package ru.aao.geolocation.biz.stubs

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.GlSettings
import ru.aao.geolocation.common.models.*
import ru.aao.geolocation.common.stubs.GlStubs
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.stubValidateBadDescription(description: String, corSetting: GlSettings) = worker {
    this.description = description
    active { stubCase == GlStubs.BAD_PERSON_ID && state == GlState.RUNNING}
    handle {
        errors.add(
            GlError(
                code = "validation",
                group = "validation",
                message = "Wrong personId field"
            )
        )
    }
}