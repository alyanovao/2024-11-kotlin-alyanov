package ru.aao.geolocation.common.models

@JvmInline
value class DeviceId(private val id: Long) {
    fun asLong() = id

    companion object {
        val NONE = DeviceId(0L)
    }
}