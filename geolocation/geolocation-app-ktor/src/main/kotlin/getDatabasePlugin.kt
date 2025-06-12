package ru.aao.geolocation.common.ktor

import io.ktor.server.application.*
import ru.aao.geolocation.common.repository.IRepository

//expect fun Application.getDatabasePlugin(type: DbType): IRepository