package ru.aao.geolocation.common.repository

import ru.aao.geolocation.common.helpers.errorSystem
import ru.aao.geolocation.common.models.GlError
import ru.aao.geolocation.common.models.PersonId

const val ERROR_GROUP_REPO = "Repo"

fun errorNoFound(personId: PersonId) = DBGlResponseErr(
    GlError(
        code = "$ERROR_GROUP_REPO-no-found",
        group = ERROR_GROUP_REPO,
        field = "personId",
        message = "Object with personId $personId no found"
    )
)

val errorEmptyId = DBGlResponseErr(
    GlError(
        code = "$ERROR_GROUP_REPO-empty-personId",
        group = ERROR_GROUP_REPO,
        field = "personId",
        message = "Must by no empty"
    )
)

fun errorDb(e: RepoException) = DBGlResponseErr(
    errorSystem(
        violationCode = "dbLockEmpty",
        e = e
    )
)