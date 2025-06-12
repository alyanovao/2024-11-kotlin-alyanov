package ru.aao.geolocation.biz.stubs

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.common.models.GlWorkMode
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.chain

fun ICorChainDsl<GeolocationContext>.stubs(title: String, block: ICorChainDsl<GeolocationContext>.() -> Unit = {}) = chain {
    block()
    this.title = title
    active { workMode == GlWorkMode.STUB && state == GlState.RUNNING }
}