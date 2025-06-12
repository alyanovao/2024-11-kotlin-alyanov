package ru.aao.geolocation.common.repository

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.BaseGeolocation
import ru.aao.geolocation.common.models.GlError

sealed interface IDBGlsResponse: IDBResponse<List<GeolocationContext>>

data class DBGlsResponseOk(
    val data: List<BaseGeolocation>
): IDBGlsResponse

data class DBGlsResponseErr(
    val errors: List<GlError> = emptyList()
): IDBGlsResponse {
    constructor(err: GlError): this(listOf(err))
}