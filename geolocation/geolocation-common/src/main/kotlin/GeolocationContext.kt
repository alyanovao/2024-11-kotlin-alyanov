import kotlinx.datetime.Instant
import models.*
import stubs.GlStubs

data class GeolocationContext (
    var command: GlCommand = GlCommand.NONE,
    var state: GlState = GlState.NONE,
    val errors: MutableList<GlError> = mutableListOf(),

    var workMode: GlWorkMode = GlWorkMode.PROD,
    var stubCase: GlStubs = GlStubs.NONE,

    var requestId: GlRequestId = GlRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var location: BaseGeolocation = BaseGeolocation(),
    var filterRequest: GlFilterRequest = GlFilterRequest(),

    var glResponse: BaseGeolocation = BaseGeolocation(),
    val glResponseList: MutableList<BaseGeolocation> = mutableListOf()
)