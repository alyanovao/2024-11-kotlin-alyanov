package ru.aao.geolocation.common.models

@JvmInline
value class Bearing (private val value: Double) {
    fun asDouble() = value
    companion object {
        val NONE = Bearing(0.0)
    }
}
