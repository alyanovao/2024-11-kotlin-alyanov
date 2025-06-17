package ru.aao.geolocation.common.repository

import ru.aao.geolocation.common.models.PersonId

data class DBGlFilterRequest(
    val messageFilter: String = "",
    val ownerId: PersonId = PersonId.NONE
)