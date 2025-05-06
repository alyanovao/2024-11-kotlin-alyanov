package ru.aao.geolocation.common.ktor

import ru.aao.geolocation.biz.GlProcessor
import ru.aao.geolocation.app.common.IGlAppSettings
import ru.aao.geolocation.common.GlSettings
import java.util.Collections.emptyList

data class GlAppSetting(
    val appUrls: List<String> = emptyList(),
    override val corSettings: GlSettings = GlSettings(),
    override val processor: GlProcessor = GlProcessor(corSettings)
): IGlAppSettings