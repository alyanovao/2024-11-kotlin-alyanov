package ru.aao.geolocation.repository.inmemory

import com.benasher44.uuid.uuid4
import io.github.reactivecircus.cache4k.Cache
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.BaseGeolocation
import ru.aao.geolocation.common.models.GlLock
import ru.aao.geolocation.common.models.PersonId
import ru.aao.geolocation.common.repository.*
import ru.aao.geolocation.common.repository.exception.RepoEmptyLockException
import ru.aao.geolocation.repository.common.IRepoInitialize
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

class RepoInMemory (
    ttl: Duration = 2.minutes,
    val randomUuid: () -> String = {uuid4().toString()}
): GeolocationRepoBase(), IRepository, IRepoInitialize {
    private val mutex = Mutex()
    private val cache = Cache.Builder<String, GlEntity>()
    .expireAfterWrite(ttl)
    .build()

    override fun save(gls: Collection<BaseGeolocation>) = gls.map { gl ->
        val entity = GlEntity(gl)
        require(entity.personId != null)
        cache.put(entity.personId, entity)
        gl
    }

    override suspend fun create(request: DBGlRequest): IDBGlResponse = tryGlMethod {
        val randomKey = Random.nextLong(Long.MIN_VALUE, Long.MAX_VALUE)
        val key = randomKey.toString()
        val gl = request.request.copy(
            personId = PersonId(randomKey), lock = GlLock(key.toString())
        )
        val entity = GlEntity(gl)
        mutex.withLock{
            cache.put(key, entity)
        }
        DBGlResponseOk(gl)
    }

    override suspend fun readCurrent(request: DBGlIdRequest): IDBGlResponse = tryGlMethod{
        val key = request.personId.toString()
        mutex.withLock{
            cache.get(key)
                ?.let {
                    DBGlResponseOk(it.toInternal())
                } ?: errorNoFound(request.personId)
        }
    }

    override suspend fun readAll(request: DBGlIdRequest): IDBGlsResponse = tryGlsMethod{
        val result: List<BaseGeolocation> = cache.asMap().asSequence()
            .filter { entity ->
                request.personId.takeIf { it != PersonId.NONE }?.let {
                    it.asLong() == entity.key
                } ?: true
            }
            .map { it.value.toInternal() }
            .toList()
        DBGlsResponseOk(result)
    }

    override suspend fun update(request: DBGlRequest): IDBGlResponse = tryGlMethod{
        val request = request.request
        val id = request.personId
        val key = request.personId.toString()
        mutex.withLock {
            val oldPersonId = cache.get(key)?.toInternal()
            when {
                oldPersonId == null -> errorNoFound(id)
                oldPersonId.lock == GlLock.NONE -> errorDb(RepoEmptyLockException(id))
                else -> {
                    val newGl = request.copy(lock = GlLock(randomUuid()))
                    val entity = GlEntity(newGl)
                    cache.put(key, entity)
                    DBGlResponseOk(newGl)
                }
            }
        }
    }

    override suspend fun delete(request: DBGlIdRequest): IDBGlResponse = tryGlMethod{
        val id = request.personId
        val key = id.toString()
        mutex.withLock {
            val oldGl = cache.get(key)?.toInternal()
            when {
                oldGl == null -> errorNoFound(id)
                oldGl.lock == GlLock.NONE -> errorDb(RepoEmptyLockException(id))
                else -> {
                    cache.invalidate(key)
                    DBGlResponseOk(oldGl)
                }
            }
        }
    }
}