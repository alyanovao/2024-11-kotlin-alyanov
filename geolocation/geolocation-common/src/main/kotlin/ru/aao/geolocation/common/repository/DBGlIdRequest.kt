package ru.aao.geolocation.common.repository

import ru.aao.geolocation.common.models.BaseGeolocation
import ru.aao.geolocation.common.models.PersonId

data class DBGlIdRequest(
    val personId: PersonId
) {
    constructor(request: BaseGeolocation): this(request.personId)
}