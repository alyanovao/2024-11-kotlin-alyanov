package ru.aao.geolocation.biz.validation

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.Altitude
import ru.aao.geolocation.common.models.GlError
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.validateDescriptionNotEmpty(description: String) = worker() {
    this.description = "Валидация"
    this.active { validating.altitude == Altitude.NONE }
    this.except {
        errors.add(
            GlError(field = "altitude", message = "Altitude is empty"
        ))
    }
}