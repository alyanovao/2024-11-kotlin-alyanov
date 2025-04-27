package ru.aao.geolocation.app.ktor

import io.ktor.server.application.*
import ru.aao.geolocation.api.v1.models.*
import kotlin.reflect.KClass

val clCreate: KClass<*> = ApplicationCall::createGeolocation::class
suspend fun ApplicationCall.createGeolocation(appSetting: GlAppSetting) =
    process<ICreateLocationRequest, ICreateLocationResponse>(appSetting, clCreate, "create")

val clReadCurrent: KClass<*> = ApplicationCall::readCurrentGeolocation::class
suspend fun ApplicationCall.readCurrentGeolocation(appSetting: GlAppSetting) =
    process<IReadCurrentLocationRequest, IReadCurrentLocationResponse>(appSetting, clReadCurrent, "readCurrent")

val clReadAll: KClass<*> = ApplicationCall::readAllGeolocation::class
suspend fun ApplicationCall.readAllGeolocation(appSetting: GlAppSetting) =
    process<IReadAllLocationRequest, IReadAllLocationResponse>(appSetting, clReadAll, "readAll")

val clUpdate: KClass<*> = ApplicationCall::updateGeolocation::class
suspend fun ApplicationCall.updateGeolocation(appSetting: GlAppSetting) =
    process<IUpdateLocationRequest, IUpdateLocationResponse>(appSetting, clUpdate, "update")

val clDelete: KClass<*> = ApplicationCall::deleteGeolocation::class
suspend fun ApplicationCall.deleteGeolocation(appSetting: GlAppSetting) =
    process<IDeleteLocationRequest, IDeleteLocationResponse>(appSetting, clDelete, "delete")