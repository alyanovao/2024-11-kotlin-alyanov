package ru.aao.geolocation.biz.repo

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.common.repository.*
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.repoReadAll(title: String) = worker {
    this.title = title
    this.description = "Получение объектов из БД"
    active { state == GlState.RUNNING }
    handle {
        val request = DBGlRequest(validated)
        when(val result = glRepo.readAll(request)) {
            is DBGlsResponseOk -> glsRepoDone = result.data.toMutableList()
            is DBGlsResponseErr -> errors = result.errors.toMutableList()
        }
    }
}