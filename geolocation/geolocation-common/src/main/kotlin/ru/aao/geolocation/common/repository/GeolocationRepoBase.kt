package ru.aao.geolocation.common.repository

import ru.aao.geolocation.common.helpers.errorSystem

abstract class GeolocationRepoBase: IRepository {

    protected suspend fun tryGlMethod(block: suspend() -> IDBGlResponse) = try {
        block()
    } catch (e: Throwable) {
        DBGlResponseErr(errorSystem("method exception", e = e))
    }

    protected suspend fun tryGlsMethod(block: suspend() -> IDBGlsResponse) = try {
        block()
    } catch (e: Throwable) {
        DBGlsResponseErr(errorSystem("method exception", e = e))
    }
}