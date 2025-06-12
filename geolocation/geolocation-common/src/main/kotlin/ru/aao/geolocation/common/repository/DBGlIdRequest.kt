package ru.aao.geolocation.common.repository

import ru.aao.geolocation.common.models.BaseGeolocation
import ru.aao.geolocation.common.models.GeolocationId

data class DBGlIdRequest(
    val id: GeolocationId
) {
    constructor(request: BaseGeolocation): this(request.id)
}