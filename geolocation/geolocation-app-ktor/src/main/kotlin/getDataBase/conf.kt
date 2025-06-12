package ru.aao.geolocation.common.ktor.getDataBase

import io.ktor.server.application.*
import ru.aao.geolocation.common.ktor.DbType
import ru.aao.geolocation.common.ktor.configuration.ConfigPaths
import ru.aao.geolocation.common.ktor.configuration.PostgresConfiguration
import ru.aao.geolocation.common.repository.IRepository
import ru.aao.geolocation.repository.inmemory.RepoInMemory
import ru.aao.geolocation.repository.postgres.RepoSql
import ru.aao.geolocation.repository.postgres.SqlProperties
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

fun Application.getDataBaseConf(type: DbType): IRepository {
    val dbPath = "${ConfigPaths.repository}.${type.typeName}"
    val dbSetting = environment.config.propertyOrNull(dbPath)?.getString()?.lowercase() ?: "postgres"
    //dbSetting = "postgres"
    return when (dbSetting) {
        "in-memory", "inmemory", "memory" -> initInmemory()
        "postgres" -> iniPostgres()
        else -> throw IllegalArgumentException("$dbSetting should be inmemory, postges")
    }
}


fun Application.initInmemory(): IRepository {
    val ttl = environment.config.propertyOrNull("db.prod")?.getString()?.let {
        Duration.parse(it)
    }
    return RepoInMemory(ttl = ttl ?: 10.minutes)
}

fun Application.iniPostgres(): IRepository {
    val config = PostgresConfiguration(environment.config)
    return RepoSql(
        properties = SqlProperties(
            host = config.host,
            port = config.port,
            user = config.user,
            password = config.password,
            schema = config.schema,
            database = config.database
        )
    )
}