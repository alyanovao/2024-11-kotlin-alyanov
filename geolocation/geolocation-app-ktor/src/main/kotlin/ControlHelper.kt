package ru.aao.geolocation.common.ktor

import fromTransport
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.aao.geolocation.api.v1.models.IRequest
import ru.aao.geolocation.api.v1.models.IResponse
import toTransport
import kotlin.reflect.KClass

suspend inline fun <reified Q: IRequest, reified S: IResponse> ApplicationCall.process(
    appSetting: GlAppSetting,
    clazz: KClass<*>,
    logId: String
) = appSetting.controllerHelper(
    {
        fromTransport(receive<Q>())
    },
    {
        respond(toTransport())
    },
    clazz,
    logId
)