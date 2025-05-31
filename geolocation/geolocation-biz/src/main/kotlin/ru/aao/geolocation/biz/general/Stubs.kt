package ru.aao.geolocation.biz.general

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.common.models.GlWorkMode
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.chain

fun ICorChainDsl<GeolocationContext>.stubs(description: String,
                                           block: ICorChainDsl<GeolocationContext>.() -> Unit
) = chain {
    block()
    this.description = description
    active { this.workMode == GlWorkMode.STUB && this.state == GlState.RUNNING }
}