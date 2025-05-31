package ru.aao.geolocation.common

import ru.aao.geolocation.lib.logging.GlLoggerProvider

data class GlSettings(
    val logProvider: GlLoggerProvider = GlLoggerProvider()
) {
    companion object {
        val NONE = GlSettings()
    }
}