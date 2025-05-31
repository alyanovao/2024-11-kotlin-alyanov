package ru.aao.geolocation.biz.validation

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlError
import ru.aao.geolocation.common.models.PersonId
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.validatePersonIdIsNotEmpty(description: String) = worker {
    this.description = description
    active { validating.personId == PersonId.NONE }
    handle {
        errors.add(
            GlError(
                field = "personId",
                code = "empty",
                message = "field must ne not empty"
            )
        )
    }

}