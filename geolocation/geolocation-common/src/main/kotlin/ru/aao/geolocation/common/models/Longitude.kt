package ru.aao.geolocation.common.models

@JvmInline
value class Longitude (private val locate: Double) {
    fun asDouble() = locate

    companion object {
        val NONE = Longitude(0.0)
    }
}
