package ru.aao.geolocation.common.repository

interface IRepository {
    suspend fun create(request: DBGlRequest): IDBGlResponse
    suspend fun readCurrent(request: DBGlIdRequest): IDBGlResponse
    suspend fun readAll(request: DBGlIdRequest): IDBGlsResponse
    suspend fun update(request: DBGlRequest): IDBGlResponse
    suspend fun delete(request: DBGlIdRequest): IDBGlResponse
    companion object {
        val NONE = object : IRepository {
            override suspend fun create(request: DBGlRequest): IDBGlResponse {
                TODO("Not yet implemented")
            }

            override suspend fun readCurrent(request: DBGlIdRequest): IDBGlResponse {
                TODO("Not yet implemented")
            }

            override suspend fun readAll(request: DBGlIdRequest): IDBGlsResponse {
                TODO("Not yet implemented")
            }

            override suspend fun update(request: DBGlRequest): IDBGlResponse {
                TODO("Not yet implemented")
            }

            override suspend fun delete(request: DBGlIdRequest): IDBGlResponse {
                TODO("Not yet implemented")
            }
        }
    }
}