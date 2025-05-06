plugins {
    id("build-jvm")
    alias(libs.plugins.kotlinx.serialization)
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.serialization.json)
    implementation(project(":geolocation-api"))
    implementation(project(":geolocation-biz"))
    implementation(project(":geolocation-common"))
}