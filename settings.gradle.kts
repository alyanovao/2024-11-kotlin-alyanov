rootProject.name = "2024-11-kotlin-alyanov"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

//enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

includeBuild("geolocation")
includeBuild("geolocation-libs")