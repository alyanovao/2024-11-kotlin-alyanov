package ru.aao.geolocation.common.ktor.configuration

import io.ktor.server.config.*

class PostgresConfiguration (
    val host: String = "localhost",
    val port: Int = 5432,
    val user: String = "postgres",
    val password: String = "postgres",
    val database: String = "local",
    val schema: String = "public"
) {
    constructor(config: ApplicationConfig) : this(
        host = config.propertyOrNull("host")?.getString() ?: "localhost",
        port = config.propertyOrNull("port")?.getString()?.toIntOrNull() ?: 5432,
        user = config.propertyOrNull("user")?.getString() ?: "postgres",
        password = config.propertyOrNull("password")?.getString() ?: "postgres",
        database = config.propertyOrNull("database")?.getString() ?: "postgres",
        schema = config.propertyOrNull("schema")?.getString() ?: "public"
    )
    companion object {
        const val PATH = "${ConfigPaths.repository}.psql"
    }
}