plugins {
    id("build-jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("test"))
    implementation(project(":geolocation-common"))
    implementation(project(":geolocation-stub"))
}