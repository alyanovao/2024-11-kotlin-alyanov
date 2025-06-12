package ru.aao.geolocation.common.ktor

import ru.aao.geolocation.biz.GlProcessor
import io.ktor.server.application.*
import ru.aao.geolocation.lib.logging.GlLoggerProvider
import ru.aao.geolocation.common.GlSettings
import ru.aao.geolocation.common.ktor.getDataBase.getDataBaseConf
import ru.aao.geolocation.repository.inmemory.RepoInMemory
import ru.aao.geolocation.repository.stub.RepoStub

fun Application.initAppSettings(): GlAppSetting {
    val setting = GlSettings(
        logProvider = GlLoggerProvider(),
        repoTest = getDataBaseConf(DbType.TEST),
        repoProd = getDataBaseConf(DbType.PROD),
        repoStub = RepoStub()
    )
    return GlAppSetting(
        appUrls = environment.config.propertyOrNull("ktor.urls")?.getList() ?: emptyList(),
        corSettings = setting,
        processor = GlProcessor(setting)
    )
}