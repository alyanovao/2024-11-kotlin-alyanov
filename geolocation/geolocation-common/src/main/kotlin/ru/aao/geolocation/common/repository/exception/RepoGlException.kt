package ru.aao.geolocation.common.repository.exception

import ru.aao.geolocation.common.models.GeolocationId
import ru.aao.geolocation.common.repository.RepoException

open class RepoGlException (
    val id: GeolocationId,
    message: String
): RepoException(message)