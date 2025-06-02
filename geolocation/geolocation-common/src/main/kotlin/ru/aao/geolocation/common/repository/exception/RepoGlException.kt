package ru.aao.geolocation.common.repository.exception

import ru.aao.geolocation.common.models.PersonId
import ru.aao.geolocation.common.repository.RepoException

open class RepoGlException (
    val personId: PersonId,
    message: String
): RepoException(message)