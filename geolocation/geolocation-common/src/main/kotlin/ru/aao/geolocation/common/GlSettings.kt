package ru.aao.geolocation.common

import logger.GlLoggerProvider

data class GlSettings(
    val logProvider: GlLoggerProvider = GlLoggerProvider()
) {
    companion object {
        val NONE = GlSettings()
    }
}