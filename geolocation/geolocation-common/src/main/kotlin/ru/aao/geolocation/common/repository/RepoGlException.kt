package ru.aao.geolocation.common.repository

import ru.aao.geolocation.common.models.PersonId

open class RepoGlException(
    val personId: PersonId,
    message: String
): RepoException(message)