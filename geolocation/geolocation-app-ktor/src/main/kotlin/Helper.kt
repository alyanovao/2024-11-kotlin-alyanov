package ru.aao.geolocation.common.ktor

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.app.common.IGlAppSettings
import fromTransport
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.datetime.Clock
import ru.aao.geolocation.common.models.GlError
import ru.aao.geolocation.common.models.GlState
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

suspend inline fun <T> IGlAppSettings.controllerHelper(
    crossinline getRequest: suspend GeolocationContext.() -> Unit,
    crossinline toResponse: suspend GeolocationContext.() -> T,
    clazz: KClass<*>,
    logId: String
): T {
    val logger = corSettings.logProvider.logger(clazz)
    val ctx = GeolocationContext(
        timeStart = Clock.System.now()
    )
    return try {
        ctx.getRequest()
        logger.info(
            msg = "Request $logId",
            marker = "BIZ",
            data = ctx.toLog(logId)
        )
        processor.exec(ctx)
        ctx.toResponse()
    }
    catch (e: Throwable) {
        logger.error(
            msg = "",
            marker = "BIZ",
            data = ctx.toLog(logId))
        ctx.state = GlState.FAILING
        ctx.errors.add(GlError(message = e.message ?: ""))
        processor.exec(ctx)
        ctx.toResponse()
    }
}