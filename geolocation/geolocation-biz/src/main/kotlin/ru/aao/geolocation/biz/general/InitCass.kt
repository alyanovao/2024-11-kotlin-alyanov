package ru.aao.geolocation.biz.general

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.initStatus(title: String) = worker() {
    this.title = title
    this.description = "Инициализация статуса"
    active {
        state == GlState.NONE
    }
    handle {
        state = GlState.RUNNING
    }
}