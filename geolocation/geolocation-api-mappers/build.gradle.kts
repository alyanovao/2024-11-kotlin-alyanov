plugins {
    id("build-jvm")
}

group = rootProject.group
version =rootProject.version

dependencies{
    implementation(kotlin("stdlib"))
    implementation(project(":geolocation-api"))
    implementation(project(":geolocation-common"))
    implementation(libs.kotlinx.datetime)
    testImplementation(kotlin("test-junit"))
}