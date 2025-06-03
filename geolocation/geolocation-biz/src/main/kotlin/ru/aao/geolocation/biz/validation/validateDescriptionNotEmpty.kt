package ru.aao.geolocation.biz.validation

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.Altitude
import ru.aao.geolocation.common.models.GlError
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.validateDescriptionNotEmpty(title: String) = worker() {
    this.title = title
    this.description = "Валидация"
    active { validating.altitude == Altitude.NONE }
    handle {
        errors.add(
            GlError(field = "altitude", message = "Altitude is empty"
        ))
    }
}