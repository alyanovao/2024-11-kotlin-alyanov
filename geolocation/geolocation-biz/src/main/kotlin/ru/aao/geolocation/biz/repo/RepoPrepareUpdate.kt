package ru.aao.geolocation.biz.repo

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.repoPrepareUpdate(title: String) = worker {
    this.title = title
    this.description = "Подготовка к сохранению в БД"
    active { state == GlState.RUNNING }
    handle {
        glRepoPrepare = glRepoRead.deepCopy().apply {
            this.personId = validated.personId
            this.deviceId = validated.deviceId
            this.latitude = validated.latitude
            this.longitude = validated.longitude
            this.altitude = validated.altitude
        }
    }
}