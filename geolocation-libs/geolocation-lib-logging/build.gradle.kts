plugins {
    id("build-jvm")
}

dependencies {
    implementation("ru.aao.geolocation:geolocation-api")
    implementation(libs.logback)

    implementation(libs.logback.logstash)
    api(libs.logback.appenders)
}