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
                implementation(kotlin("stdlib-jdk8"))
                api(libs.kotlinx.datetime)
                implementation("com.benasher44:uuid:0.8.4")
            }
        }
    }
}

dependencies{
    implementation("ru.aao.geolocation.libs:geolocation-lib-logging")
}