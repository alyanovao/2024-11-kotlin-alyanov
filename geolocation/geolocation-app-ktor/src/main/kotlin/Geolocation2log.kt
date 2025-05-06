package ru.aao.geolocation.common.ktor

import CommonModel
import ru.aao.geolocation.common.GeolocationContext

fun GeolocationContext.toLog(logId: String) = CommonModel(
    id = logId,
    description = ""
)