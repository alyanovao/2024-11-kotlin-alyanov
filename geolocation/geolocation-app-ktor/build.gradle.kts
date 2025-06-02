import io.ktor.plugin.features.*

plugins {
    alias(libs.plugins.kotlinx.serialization)
    id("build-jvm")
    alias(libs.plugins.ktor)
    id("com.bmuschko.docker-remote-api") version "9.4.0"
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

ktor {
    configureNativeImage(project)
    docker {
        localImageName.set(project.name)
        imageTag.set(project.version.toString())
        jreVersion.set(JavaVersion.VERSION_21)
    }
}

jib {
    container.mainClass = application.mainClass.get()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.yaml)
    implementation(libs.ktor.server.headers.response)
    implementation(libs.ktor.server.headers.default)
    implementation(libs.ktor.serialization.jackson)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.server.negotiation)

    implementation(project(":geolocation-api"))
    implementation(project(":geolocation-api-mappers"))
    implementation(project(":geolocation-common"))
    implementation(project(":geolocation-app-common"))
    implementation(project(":geolocation-biz"))
    implementation("ru.aao.geolocation.libs:geolocation-lib-logging")
    implementation("io.ktor:ktor-server-caching-headers:2.3.12")
    implementation("io.ktor:ktor-server-call-logging:2.3.12")
    implementation(kotlin("test"))
    implementation(kotlin("test-common"))
    implementation(kotlin("test-annotations-common"))
    implementation(libs.ktor.server.test)
    implementation(project(":geolocation-stub"))
    implementation(project(":geolocation-repository-common"))
    implementation(project(":geolocation-repository-inmemory"))
    implementation(project(":geolocation-repository-stubs"))
}