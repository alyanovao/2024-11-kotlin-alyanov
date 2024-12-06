rootProject.name = "2024-11-kotlin-alyanov"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
    }
}

include("homework1")