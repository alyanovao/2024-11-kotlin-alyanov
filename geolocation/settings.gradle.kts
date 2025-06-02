rootProject.name = "ru.aao.geolocation"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

pluginManagement {
    includeBuild("../build-plugin")
    plugins {
        id("build-jvm") apply false
        id("build-kmp") apply false
    }
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":geolocation-api")
include(":geolocation-api-mappers")
include(":geolocation-common")
include(":geolocation-app-common")
include(":geolocation-app-ktor")
include(":geolocation-app-kafka")
include(":geolocation-biz")
include(":geolocation-stub")
include(":geolocation-repository-common")
include(":geolocation-repository-inmemory")
include(":geolocation-repository-stubs")