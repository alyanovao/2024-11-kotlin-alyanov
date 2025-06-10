package ru.aao.geolocation.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait

internal class BuildPGContainer: Plugin<Project> {
    val username = "geolocation"
    val password = "geolocation"
    val db = "custom"

    private val pgContainer = PostgreSQLContainer<Nothing>("postgres:latest").apply {
        withUsername(username)
        withPassword(password)
        withDatabaseName(db)
        waitingFor(Wait.forLogMessage("database is ready to start", 1))
    }

    override fun apply(project: Project): Unit = with(project) {
        val stopTask = tasks.register("pgStop") {
            group = "containers"
            pgContainer.stop()
        }
        tasks.register("pgStart", PgContainerStartTask::class.java) {
            pgContainer.start()
            pgUrl = pgContainer.jdbcUrl
            finalizedBy(stopTask)
        }
    }
}