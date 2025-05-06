plugins {
    id("build-jvm")
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.server.headers.default)
    implementation(project(":geolocation-common"))
}