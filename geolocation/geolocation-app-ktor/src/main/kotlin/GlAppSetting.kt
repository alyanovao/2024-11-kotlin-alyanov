package ru.aao.geolocation.app.ktor

import GlProcessor
import GlSettings
import IGlAppSettings
import java.util.Collections.emptyList

data class GlAppSetting(
    val appUrls: List<String> = emptyList(),
    override val corSettings: GlSettings = GlSettings(),
    override val processor: GlProcessor = GlProcessor(corSettings)
): IGlAppSettings