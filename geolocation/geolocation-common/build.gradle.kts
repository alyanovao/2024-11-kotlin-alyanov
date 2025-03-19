plugins {
    id("build-jvm")
    alias(libs.plugins.kotlinx.serialization)
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.kotlinx.datetime)
}