package ru.aao.geolocation.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class PersonId(private val id: Long) {
    fun asLong() = id

    companion object {
        val NONE = PersonId(0L)
    }
}