package ru.aao.geolocation.biz.repo

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.common.models.PersonId
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.repoPrepareCreate(title: String) = worker {
    this.title = title
    this.description = "Подготовка к сохранению в БД"
    active { state == GlState.RUNNING }
    handle {
        glRepoPrepare = validated.deepCopy()
        glRepoPrepare.personId = PersonId.NONE

    }
}