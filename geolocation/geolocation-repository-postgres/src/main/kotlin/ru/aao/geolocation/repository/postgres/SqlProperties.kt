package ru.aao.geolocation.repository.postgres

data class SqlProperties(
    val host: String = "localhost",
    val port: Int = 5432,
    val user: String = "postgres",
    val password: String = "postgres",
    val schema: String = "public",
    val database: String = "public",
    val table: String = "location"
) {
    val url: String = "jdbc:postgresql://${host}:${port}/${database}"
}