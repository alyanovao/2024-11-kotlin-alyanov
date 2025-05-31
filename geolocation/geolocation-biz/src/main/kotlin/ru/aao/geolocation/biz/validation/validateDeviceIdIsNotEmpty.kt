package ru.aao.geolocation.biz.validation

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.DeviceId
import ru.aao.geolocation.common.models.GlError
import ru.aao.geolocation.common.models.PersonId
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.validateDeviceIdIsnotEmpty(description: String) = worker {
    this.description = description
    active { validating.deviceId == DeviceId.NONE }
    handle {
        errors.add(
            GlError(
                field = "deviceId",
                code = "empty",
                message = "field must ne not empty"
            )
        )
    }

}