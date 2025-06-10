plugins {
    id("build-jvm")
}

repositories {
    google()
    mavenCentral()
}

dependencies{
    implementation(libs.db.postgres)
    implementation(libs.db.exposed.dao)
    implementation(libs.db.exposed.core)
    implementation(project(":geolocation-common"))
    implementation(project(":geolocation-repository-common"))
    implementation("ru.aao.geolocation.libs:geolocation-lib-logging")
}