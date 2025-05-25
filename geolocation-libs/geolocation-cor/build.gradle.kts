plugins {
    id("build-jvm")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.server.headers.default)
}