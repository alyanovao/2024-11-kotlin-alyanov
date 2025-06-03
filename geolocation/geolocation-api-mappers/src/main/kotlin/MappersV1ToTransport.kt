import ru.aao.geolocation.api.v1.models.*
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.exceptions.UnknownGeolocationCommand
import ru.aao.geolocation.common.models.*
import ru.aao.geolocation.common.models.BaseGeolocation

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

fun MutableList<BaseGeolocation>.toTransport() = this
    .map{it.toTransport()}
    .toList()

internal fun BaseGeolocation.toTransport(): ResponseObject = ResponseObject(
    id = id.takeIf { it != GeolocationId.NONE }?.asLong(),
    personId = personId.takeIf { it != PersonId.NONE }?.asLong(),
    deviceId = deviceId.takeIf { it != DeviceId.NONE }?.asLong(),
    longitude = longitude.takeIf { it != Longitude.NONE }?.asDouble(),
    latitude = latitude.takeIf { it != Latitude.NONE }?.asDouble(),
    bearing = bearing.takeIf { it != Bearing.NONE }?.asDouble(),
    altitude = altitude.takeIf { it != Altitude.NONE }?.asDouble(),
    eventDateTime = eventDateTime.takeIf { it != EventDateTime.NONE }?.asString(),
    batteryLevel = batteryLevel.takeIf { it != BatteryLevel.NONE }?.asFloat(),
    permissions = permissionClient.toTransport()
)

internal fun GlPermissionClient.toTransport() = when(this) {
    GlPermissionClient.READ -> Permissions.READ
    GlPermissionClient.UPDATE -> Permissions.UPDATE
    GlPermissionClient.DELETE -> Permissions.DELETE
}

internal fun Set<GlPermissionClient>.toTransport(): Set<Permissions>? = this
    .map { it.toTransport() }
    .toSet()
    .takeIf { it.isNotEmpty() }

internal fun GeolocationContext.toTransportReadCurrent() = IReadCurrentLocationResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    gl = glResponse.toTransport()
)

internal fun GeolocationContext.toTransportReadAll() = IReadAllLocationResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    gls = glResponseList.toTransport()
)

internal fun GeolocationContext.toTransportUpdate() = IUpdateLocationResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    gl = glResponse.toTransport()
)

internal fun GeolocationContext.toTransportDelete() = IDeleteLocationResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    gl = glResponse.toTransport()
)

internal fun GeolocationContext.toTransportSearch() = ISearchLocationResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    gls = glResponseList.toTransport()
)

internal fun GlState.toResult(): ResponseResult? = when (this) {
    GlState.RUNNING -> ResponseResult.SUCCESS
    GlState.FAILING -> ResponseResult.ERROR
    GlState.FINISHED -> ResponseResult.SUCCESS
    GlState.NONE -> null
}

internal fun List<GlError>.toTransportErrors(): List<Error>? = this
    .map{ it.toTransportGl() }
    .toList()
    .takeIf { it.isNotEmpty() }

internal fun GlError.toTransportGl() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() }
)