package ru.aao.geolocation.cor

import ru.aao.geolocation.biz.GlProcessor
import ru.aao.geolocation.common.GlSettings

interface IGlAppSettings {
    val processor: GlProcessor
    val corSettings: GlSettings
}