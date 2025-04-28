package ru.aao.geolocation.common.models

@JvmInline
value class EventDateTime(private val value: String) {
    fun asString() = value
    companion object {
        val NONE = EventDateTime("")
    }
}