package ru.aao.geolocation.common.ktor

import ru.aao.geolocation.repository.postgres.SqlProperties

data class AddConfigPostgres (
    val host: String = "localhost",
    val port: Int = 5432,
    val user: String = "postgres",
    val password: String = "postgres",
    val database: String = "postgres",
    val schema: String = "public",
    val table: String = "geolocationStorage"
) {
    val psql: SqlProperties = SqlProperties(
        host = host,
        port = port,
        user = user,
        password = password,
        database = database,
        schema = schema,
        table = table
    )
}