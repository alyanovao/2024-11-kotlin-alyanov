plugins {
    id("build-jvm")
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        val coroutineVersion: String by project
        main {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                api(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
            }
        }
    }
}

dependencies{
    implementation(project(":geolocation-libs"))
}