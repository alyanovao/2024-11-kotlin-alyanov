package ru.aao.geolocation.common.models

data class GlFilterRequest (
    var searchString: String = "",
    var personId: PersonId = PersonId.NONE
)