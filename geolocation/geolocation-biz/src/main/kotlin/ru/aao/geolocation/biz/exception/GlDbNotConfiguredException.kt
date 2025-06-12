package ru.aao.geolocation.biz.exception

import ru.aao.geolocation.common.models.GlWorkMode

class GlDbNotConfiguredException(val workMode: GlWorkMode) : Exception(
    "DB not configured for mode $workMode"
)
