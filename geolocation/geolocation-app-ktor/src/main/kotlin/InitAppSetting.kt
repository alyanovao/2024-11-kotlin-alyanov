package ru.aao.geolocation.common.ktor

import ru.aao.geolocation.biz.GlProcessor
import io.ktor.server.application.*
import ru.aao.geolocation.lib.logging.GlLoggerProvider
import ru.aao.geolocation.common.GlSettings

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