package ru.aao.geolocation.biz.repo

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.common.repository.DBGlIdRequest
import ru.aao.geolocation.common.repository.DBGlsResponseErr
import ru.aao.geolocation.common.repository.DBGlsResponseOk
import ru.aao.geolocation.common.repository.DBResponseErrWithData
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.readAll(title: String) = worker {
    this.title = title
    this.description = "Получение объектов из БД"
    active { state == GlState.RUNNING }
    handle {
        val request = DBGlIdRequest(glRepoPrepare)
        when(val result = glRepo.readAll(request)) {
            is DBGlsResponseOk -> glsRepoDone = result.data.toMutableList()
            is DBGlsResponseErr -> errors = result.errors.toMutableList()
        }
    }
}