package ru.aao.geolocation.biz.stubs

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlError
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.stubNoCase(description: String) = worker {
    this.description = description
    active {state == GlState.RUNNING}
    handle {
        errors.add(
            GlError(
                code = "validation",
                field = "stub",
                group = "validation",
                message = "Wrong case for stub: ${stubCase.name}"
            )
        )
    }
}