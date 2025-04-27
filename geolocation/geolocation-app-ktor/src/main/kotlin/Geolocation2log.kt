package ru.aao.geolocation.app.ktor

import CommonModel
import GeolocationContext

fun GeolocationContext.toLog(logId: String) = CommonModel(
    id = logId,
    description = ""
)