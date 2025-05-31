plugins {
    application
    id("build-jvm")
    alias(libs.plugins.kotlinx.serialization)
}

application {
    mainClass.set("ru.aao.geolocation.app.kafka.MainKt")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "ru.aao.geolocation.app.kafka.MainKt"
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(libs.kafka.client)
    implementation(project(":geolocation-app-common"))
    implementation(project(":geolocation-common"))
    implementation(project(":geolocation-api"))
    implementation(project(":geolocation-api-mappers"))
    implementation(project(":geolocation-biz"))
    implementation(project(":geolocation-app-ktor"))
    implementation("ru.aao.geolocation.libs:geolocation-cor")
    implementation("ru.aao.geolocation.libs:geolocation-lib-logging")
    implementation("org.jetbrains.kotlinx:atomicfu:0.27.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:null")
}