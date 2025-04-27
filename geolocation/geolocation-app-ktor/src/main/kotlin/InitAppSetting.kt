package ru.aao.geolocation.app.ktor

import GlProcessor
import GlSettings
import io.ktor.server.application.*
import logger.GlLoggerProvider

fun Application.initAppSettings(): GlAppSetting {
    val setting = GlSettings(
        logProvider = GlLoggerProvider()
    )
    return GlAppSetting(
        appUrls = environment.config.propertyOrNull("ktor.urls")?.getList() ?: emptyList(),
        corSettings = setting,
        processor = GlProcessor(setting)
    )
}