package ru.aao.geolocation.common

import kotlinx.datetime.Instant
import ru.aao.geolocation.common.models.*
import ru.aao.geolocation.common.stubs.GlStubs

data class GeolocationContext (
    var command: GlCommand = GlCommand.NONE,
    var state: GlState = GlState.NONE,
    val errors: MutableList<GlError> = mutableListOf(),

    var corSettings: GlSettings = GlSettings(),
    var workMode: GlWorkMode = GlWorkMode.PROD,
    var stubCase: GlStubs = GlStubs.NONE,

    var requestId: GlRequestId = GlRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var location: BaseGeolocation = BaseGeolocation(),
    var filterRequest: GlFilterRequest = GlFilterRequest(),

    var validating: BaseGeolocation = BaseGeolocation(),
    var glFilterValidating: GlFilterRequest = GlFilterRequest(),

    var validated: BaseGeolocation = BaseGeolocation(),
    var glFilterValidated: GlFilterRequest = GlFilterRequest(),

    var glResponse: BaseGeolocation = BaseGeolocation(),
    var glResponseList: MutableList<BaseGeolocation> = mutableListOf()
)