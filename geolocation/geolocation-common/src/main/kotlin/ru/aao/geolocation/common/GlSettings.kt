package ru.aao.geolocation.common

import ru.aao.geolocation.common.repository.IRepository
import ru.aao.geolocation.lib.logging.GlLoggerProvider

data class GlSettings(
    val logProvider: GlLoggerProvider = GlLoggerProvider(),
    val repoStub: IRepository = IRepository.NONE,
    val repoTest: IRepository = IRepository.NONE,
    val repoProd: IRepository = IRepository.NONE
) {
    companion object {
        val NONE = GlSettings()
    }
}