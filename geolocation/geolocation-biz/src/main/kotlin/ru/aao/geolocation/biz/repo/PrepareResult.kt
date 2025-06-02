package ru.aao.geolocation.biz.repo

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.common.models.GlWorkMode
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.prepareResult(title: String) = worker {
    this.title = title
    this.description = "Подготовка ответа"
    active { workMode != GlWorkMode.STUB }
    handle {
        glResponse = glRepoDone
        glsRepoDone = glsRepoDone
        state = when(val st = state) {
            GlState.RUNNING -> GlState.FINISHED
            else -> st
        }
    }
}