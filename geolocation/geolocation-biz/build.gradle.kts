plugins {
    id("build-jvm")
}

dependencies {
    implementation(project(":geolocation-common"))
    implementation(project(":geolocation-stub"))
}