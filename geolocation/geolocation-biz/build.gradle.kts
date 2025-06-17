plugins {
    id("build-jvm")
}

dependencies {
    implementation(project(":geolocation-common"))
    implementation(project(":geolocation-stub"))
    implementation("ru.aao.geolocation.libs:geolocation-cor")
    implementation("ru.aao.geolocation.libs:geolocation-lib-logging")
    implementation(kotlin("test"))
    implementation(project(":geolocation-repository-inmemory"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
}