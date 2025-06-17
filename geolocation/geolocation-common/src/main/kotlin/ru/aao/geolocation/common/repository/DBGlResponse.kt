package ru.aao.geolocation.common.repository

import ru.aao.geolocation.common.models.BaseGeolocation
import ru.aao.geolocation.common.models.GlError

sealed interface IDBGlResponse: IDBResponse<BaseGeolocation>

data class DBGlResponseOk(
    val data: BaseGeolocation
): IDBGlResponse

data class DBGlResponseErr(
    val errors: List<GlError> = emptyList()
): IDBGlResponse {
    constructor(err: GlError): this(listOf(err))
}

data class DBResponseErrWithData(
    val data: BaseGeolocation,
    val errors: List<GlError> = emptyList()
): IDBGlResponse {
    constructor(gl: BaseGeolocation, err: GlError): this(gl, listOf(err))
}