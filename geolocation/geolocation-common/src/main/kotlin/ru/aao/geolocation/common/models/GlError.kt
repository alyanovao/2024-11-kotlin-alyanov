package ru.aao.geolocation.common.models

import ru.aao.geolocation.lib.logging.LogLevel

data class GlError (
    val code: String = "",
    val group: String = "",
    val field: String = "",
    val message: String = "",
    val level: LogLevel = LogLevel.ERROR,
    val exception: Throwable? = null
)
