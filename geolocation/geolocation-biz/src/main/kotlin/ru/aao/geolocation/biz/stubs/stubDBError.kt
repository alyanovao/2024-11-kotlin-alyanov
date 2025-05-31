package ru.aao.geolocation.biz.stubs

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.GlSettings
import ru.aao.geolocation.common.models.*
import ru.aao.geolocation.common.stubs.GlStubs
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.stubDBError(description: String, corSetting: GlSettings) = worker {
    this.description = description
    active { stubCase == GlStubs.DB_ERROR && state == GlState.RUNNING}
    except {
        errors.add(
            GlError(
                code = "stub",
                group = "stub-db",
                message = "Internal error"
            )
        )
    }
}