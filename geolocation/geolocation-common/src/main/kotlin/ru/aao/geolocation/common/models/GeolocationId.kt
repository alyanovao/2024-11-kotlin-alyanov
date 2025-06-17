package ru.aao.geolocation.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class GeolocationId(private val id: Long) {
    fun asLong() = id

    companion object {
        val NONE = GeolocationId(0)
    }
}