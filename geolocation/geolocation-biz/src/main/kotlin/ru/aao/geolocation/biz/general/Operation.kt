package ru.aao.geolocation.biz.general

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlCommand
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.chain

fun ICorChainDsl<GeolocationContext>.operation(
    description: String,
    command: GlCommand,
    block: ICorChainDsl<GeolocationContext>.() -> Unit
) = chain {
    block()
    this.description = description
    active { this.command == command && this.state == GlState.RUNNING }
}