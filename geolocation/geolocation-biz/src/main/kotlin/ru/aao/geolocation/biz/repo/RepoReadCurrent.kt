package ru.aao.geolocation.biz.repo

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.helpers.fail
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.common.repository.*
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.repoReadCurrent(title: String) = worker {
    this.title = title
    this.description = "Получение объекта из БД"
    active { state == GlState.RUNNING }
    handle {
        val request = DBGlIdRequest(validated)
        when(val result = glRepo.readCurrent(request)) {
            is DBGlResponseOk -> glRepoRead = result.data
            is DBGlResponseErr -> fail(result.errors)
            is DBResponseErrWithData -> {
                fail(result.errors)
                glRepoRead = result.data
            }
        }
    }
}