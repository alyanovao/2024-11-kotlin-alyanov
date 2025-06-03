package ru.aao.geolocation.biz.validation

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlError
import ru.aao.geolocation.common.models.Latitude
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.validateLocationIsNotEmpty(description: String) = worker {
    this.description = description
    active {
            validating.latitude == Latitude.NONE
    }
    handle {
        errors.add(
            GlError(
                field = "latitude",
                code = "empty",
                message = "field must ne not empty"
            )
        )
    }
}