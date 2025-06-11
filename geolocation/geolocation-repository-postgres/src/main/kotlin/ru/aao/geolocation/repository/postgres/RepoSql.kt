package ru.aao.geolocation.repository.postgres

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.aao.geolocation.common.models.BaseGeolocation
import ru.aao.geolocation.common.models.GeolocationId
import ru.aao.geolocation.common.models.GlLock
import ru.aao.geolocation.common.repository.*
import ru.aao.geolocation.repository.common.IRepoInitialize
import java.util.*

open class RepoSql(
    properties: SqlProperties,
    private val randomUuid: () -> String = { UUID.randomUUID().toString() }
): IRepository, IRepoInitialize {
    private val table = GlTable("${properties.schema}.${properties.table}")
    private val driver = when {
        properties.url.startsWith("jdbc:postgresql://") -> {"org.postgresql.Driver"}
        else -> throw IllegalArgumentException("url does not match jdbc url ${properties.url}")
    }

    private val connection = Database.connect(
        properties.url, driver, properties.user, properties.password
    )

    fun clear(): Unit = transaction(connection) {
        table.deleteAll()
    }

    private fun saveGl(request: BaseGeolocation): BaseGeolocation = transaction(connection) {
        val res = table.insert { to(it, request, randomUuid) }
            .resultedValues
            ?.map { table.from(it) }
        res?.first() ?: throw RuntimeException("Failed to insert geolocation")
    }

    private suspend inline fun <T> transactedWrapper(crossinline block: () -> T,
                                              crossinline handle: (Exception) -> T): T =
        withContext(Dispatchers.IO) {
            try {
                transaction(connection) {
                    block()
                }
            }
            catch (e: Exception) {
                handle(e)
            }
        }

    private suspend inline fun transactionWrapper(crossinline block: () -> IDBGlResponse): IDBGlResponse =
        transactionWrapper(block) { DBGlResponseErr(it.asGlError()) }

    private suspend inline fun <T> transactionWrapper(crossinline block: () -> T, crossinline handle: (Exception) -> T): T =
        withContext(Dispatchers.IO) {
            try {
                transaction(connection) {
                    block()
                }
            }
            catch (e: Exception) {
                handle(e)
            }
        }

    override fun save(gls: Collection<BaseGeolocation>): Collection<BaseGeolocation> =
        gls.map { saveGl(it) }

    override suspend fun create(request: DBGlRequest): IDBGlResponse = transactionWrapper {
        DBGlResponseOk(saveGl(request.request))
    }

    override suspend fun readCurrent(request: DBGlIdRequest): IDBGlResponse = transaction(connection) {
        val res = table.selectAll().where {
            table.id eq request.id.asLong().toString()
        }.singleOrNull() ?: return@transaction errorNoFound(request.id)
        return@transaction DBGlResponseOk(table.from(res))
    }

    override suspend fun readAll(request: DBGlRequest): IDBGlsResponse = transaction(connection) {
        val res = table.selectAll()
        return@transaction DBGlsResponseOk(data = res.map{table.from(it)})
    }

    private fun read(id: GeolocationId): IDBGlResponse {
        val res = table.selectAll().where {
            table.id eq id.asLong().toString()
        }.singleOrNull() ?: return errorNoFound(id)
        return DBGlResponseOk(table.from(res))
    }

    override suspend fun update(request: DBGlRequest): IDBGlResponse = update(request.request.id, request.request.lock) {
        table.update({ table.id eq request.request.id.asLong().toString() }) {
            to(it, request.request.copy(lock = GlLock(randomUuid())), randomUuid)
        }
        read(request.request.id)
    }

    private suspend fun update(
        id: GeolocationId,
        lock: GlLock,
        block: (BaseGeolocation) -> IDBGlResponse
    ): IDBGlResponse = transactionWrapper {
        if (id == GeolocationId.NONE) return@transactionWrapper errorEmptyId
        val current = table.selectAll().where {
            table.id eq id.asLong().toString()
        }.singleOrNull() ?.let {table.from(it)}

        when {
            current == null -> errorNoFound(id)
            else -> block(current)
        }
    }

    override suspend fun delete(request: DBGlRequest): IDBGlResponse = update(request.request.id, request.request.lock) {
        table.deleteWhere { id eq request.request.id.asLong().toString() }
        DBGlResponseOk(it)
    }
}