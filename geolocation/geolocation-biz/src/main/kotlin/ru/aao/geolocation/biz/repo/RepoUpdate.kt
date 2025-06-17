package ru.aao.geolocation.biz.repo

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.helpers.fail
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.common.repository.DBGlRequest
import ru.aao.geolocation.common.repository.DBGlResponseErr
import ru.aao.geolocation.common.repository.DBGlResponseOk
import ru.aao.geolocation.common.repository.DBResponseErrWithData
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.repoUpdate(title: String) = worker {
    this.title = title
    this.description = "Изменение объекта в БД"
    active { state == GlState.RUNNING }
    handle {
        val request = DBGlRequest(glRepoPrepare)
        when(val result = glRepo.update(request)) {
            is DBGlResponseOk -> glRepoDone = result.data
            is DBGlResponseErr -> fail(result.errors)
            is DBResponseErrWithData -> {
                fail(result.errors)
                glRepoDone = result.data
            }
        }
    }
}