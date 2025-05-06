package ru.aao.geolocation.common.models

@JvmInline
value class Latitude (private val locate: Double) {
    fun asDouble() = locate

    companion object {
        val NONE = Latitude(0.0)
    }
}
