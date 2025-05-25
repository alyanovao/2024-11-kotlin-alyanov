package ru.aao.geolocation.biz.validation

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.chain

fun ICorChainDsl<GeolocationContext>.validation(block: ICorChainDsl<GeolocationContext>.() -> Unit) = chain {
    block()
    description = "Валидация"
    active { state == GlState.RUNNING }
}