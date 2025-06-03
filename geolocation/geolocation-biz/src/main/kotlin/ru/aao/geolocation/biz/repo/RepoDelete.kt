package ru.aao.geolocation.biz.repo

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.helpers.fail
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.common.repository.*
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.repoDelete(title: String) = worker {
    this.title = title
    this.description = "Удаление объекта из БД"
    active { state == GlState.RUNNING }
    handle {
        val request = DBGlRequest(validated)
        when(val result = glRepo.delete(request)) {
            is DBGlResponseOk -> glRepoDone = result.data
            is DBGlResponseErr -> fail(result.errors)
            is DBResponseErrWithData -> fail(result.errors)
        }
    }
}