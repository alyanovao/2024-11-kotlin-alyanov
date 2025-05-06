import ru.aao.geolocation.common.exceptions.UnknownGeolocationCommand
import ru.aao.geolocation.api.v1.models.*
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.*
import ru.aao.geolocation.common.models.Altitude
import ru.aao.geolocation.common.models.DeviceId
import ru.aao.geolocation.common.models.Latitude

fun GeolocationContext.toTransport(): IResponse = when (val cmd = command) {
    GlCommand.CREATE -> toTransportCreate()
    GlCommand.READ_CURRENT  -> toTransportReadCurrent()
    GlCommand.READ_ALL  -> toTransportReadAll()
    GlCommand.UPDATE  -> toTransportUpdate()
    GlCommand.DELETE  -> toTransportDelete()
    GlCommand.SEARCH -> toTransportSearch()
    GlCommand.NONE -> throw UnknownGeolocationCommand(cmd)
}

fun GeolocationContext.toTransportCreate() = ICreateLocationResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    gl = glResponse.toTransport()
)

fun MutableList<ru.aao.geolocation.common.models.BaseGeolocation>.toTransport() = this
    .map{it.toTransport()}
    .toList()

fun ru.aao.geolocation.common.models.BaseGeolocation.toTransport(): ResponseObject = ResponseObject(
    deviceId = deviceId.takeIf { it != DeviceId.NONE }?.asLong(),
    personId = personId.takeIf { it != PersonId.NONE }?.asLong(),
    longitude = longitude.takeIf { it != Longitude.NONE }?.asDouble(),
    latitude = latitude.takeIf { it != Latitude.NONE }?.asDouble(),
    bearing = bearing.takeIf { it != Bearing.NONE }?.asDouble(),
    altitude = altitude.takeIf { it != Altitude.NONE }?.asDouble(),
    eventDateTime = eventDateTime.takeIf { it != EventDateTime.NONE }?.asString(),
    batteryLevel = batteryLevel.takeIf { it != BatteryLevel.NONE }?.asFloat(),
    permissions = permissionClient.toTransport()
)

fun GlPermissionClient.toTransport() = when(this) {
    GlPermissionClient.READ -> Permissions.READ
    GlPermissionClient.UPDATE -> Permissions.UPDATE
    GlPermissionClient.DELETE -> Permissions.DELETE
}
fun Set<GlPermissionClient>.toTransport(): Set<Permissions>? = this
    .map { it.toTransport() }
    .toSet()
    .takeIf { it.isNotEmpty() }

fun GeolocationContext.toTransportReadCurrent() = IReadCurrentLocationResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    gl = glResponse.toTransport()
)

fun GeolocationContext.toTransportReadAll() = IReadAllLocationResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    gls = glResponseList.toTransport()
)

fun GeolocationContext.toTransportUpdate() = IUpdateLocationResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    gl = glResponse.toTransport()
)

fun GeolocationContext.toTransportDelete() = IDeleteLocationResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    gl = glResponse.toTransport()
)

fun GeolocationContext.toTransportSearch() = ISearchLocationResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    gls = glResponseList.toTransport()
)

private fun GlState.toResult(): ResponseResult? = when (this) {
    GlState.RUNNING -> ResponseResult.SUCCESS
    GlState.FAILING -> ResponseResult.ERROR
    GlState.FINISHED -> ResponseResult.SUCCESS
    GlState.NONE -> null
}

private fun List<GlError>.toTransportErrors(): List<Error>? = this
    .map{ it.toTransportGl() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun GlError.toTransportGl() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() }
)