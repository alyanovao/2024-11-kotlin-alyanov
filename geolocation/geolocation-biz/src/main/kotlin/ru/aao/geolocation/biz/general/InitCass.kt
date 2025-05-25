package ru.aao.geolocation.biz.general

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.initStatus(description: String) = worker() {
    this.description = description
    active { state == GlState.NONE }
    except { state == GlState.RUNNING }
}