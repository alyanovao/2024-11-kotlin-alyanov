package ru.aao.geolocation.repository.common

import ru.aao.geolocation.common.models.BaseGeolocation
import ru.aao.geolocation.common.models.GeolocationId
import ru.aao.geolocation.common.models.GlLock

data class DbIdRequest(
    val id: GeolocationId,
    val lock: GlLock = GlLock.NONE
) {
    constructor(gl: BaseGeolocation): this(gl.id, gl.lock)
}