package ru.aao.geolocation.repository.stub

import GeolocationStubResponse.GEOLOCATION
import ru.aao.geolocation.common.repository.*

class RepoStub(): IRepository {
    override suspend fun create(request: DBGlRequest): IDBGlResponse {
        return DBGlResponseOk(
            data = GEOLOCATION
        )
    }

    override suspend fun readCurrent(request: DBGlIdRequest): IDBGlResponse {
        return DBGlResponseOk(
            data = GEOLOCATION
        )
    }

    override suspend fun readAll(request: DBGlIdRequest): IDBGlsResponse {
        return DBGlsResponseOk(
            data = listOf(GEOLOCATION)
        )
    }

    override suspend fun update(request: DBGlRequest): IDBGlResponse {
        return DBGlResponseOk(
            data = GEOLOCATION
        )
    }

    override suspend fun delete(request: DBGlIdRequest): IDBGlResponse {
        return DBGlResponseOk(
            data = GEOLOCATION
        )
    }
}