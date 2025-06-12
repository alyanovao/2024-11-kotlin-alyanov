package ru.aao.geolocation.biz.repo

import ru.aao.geolocation.biz.exception.GlDbNotConfiguredException
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.helpers.errorSystem
import ru.aao.geolocation.common.helpers.fail
import ru.aao.geolocation.common.models.GlWorkMode
import ru.aao.geolocation.common.repository.IRepository
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.worker

fun ICorChainDsl<GeolocationContext>.initRepo(title: String) = worker {
    this.title = title
    this.description = "Инициализация БД"
    handle {
        glRepo = corSettings.repoTest
        when {
            workMode == GlWorkMode.TEST -> corSettings.repoTest
            workMode == GlWorkMode.STUB -> corSettings.repoStub
            else -> corSettings.repoProd
        }
        if (workMode != GlWorkMode.STUB && glRepo == IRepository.NONE) fail(
            errorSystem(
                violationCode = "dbNotFound",
                e = GlDbNotConfiguredException(workMode)
            )
        )
    }
}
