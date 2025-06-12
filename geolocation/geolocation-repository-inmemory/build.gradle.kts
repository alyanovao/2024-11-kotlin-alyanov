plugins {
    id("build-jvm")
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        val coroutineVersion: String by project
        main {
            dependencies {
                implementation(project(":geolocation-common"))
                implementation(project(":geolocation-repository-common"))
                implementation(libs.kotlinx.coroutines)
                implementation(libs.kotlinx.test)
                implementation("com.benasher44:uuid:0.8.4")
                implementation("io.github.reactivecircus.cache4k:cache4k:0.13.0")
            }
        }
    }
}

dependencies{
    implementation("ru.aao.geolocation.libs:geolocation-lib-logging")
}