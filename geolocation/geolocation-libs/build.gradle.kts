plugins {
    id("build-jvm")
}

dependencies {
    implementation(project(":geolocation-api"))
    implementation(libs.logback)

    implementation(libs.logback.logstash)
    api(libs.logback.appenders)
}