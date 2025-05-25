package ru.aao.geolocation.common.ktor

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.lib.logging.CommonModel

fun GeolocationContext.toLog(logId: String) = CommonModel(
    id = logId,
    description = ""
)