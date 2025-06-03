import ru.aao.geolocation.api.v1.models.*
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.exceptions.RequestUnknownException
import ru.aao.geolocation.common.models.*
import ru.aao.geolocation.common.models.BaseGeolocation
import ru.aao.geolocation.common.stubs.GlStubs

fun GeolocationContext.fromTransport(request: IRequest) = when (request) {
    is ICreateLocationRequest -> fromTransport(request)
    is IReadCurrentLocationRequest -> fromTransport(request)
    is IReadAllLocationRequest -> fromTransport(request)
    is IUpdateLocationRequest -> fromTransport(request)
    is IDeleteLocationRequest -> fromTransport(request)
    else -> throw RequestUnknownException(request.javaClass)
}

fun GeolocationContext.fromTransport(request: ICreateLocationRequest) {
    command = GlCommand.CREATE
    this.location = request.gl?.toInternal() ?: BaseGeolocation()
    workMode = request.mode.transportToWorkMode()
    stubCase = request.mode.transportToStubCase()
}

fun GeolocationContext.fromTransport(request: IReadCurrentLocationRequest) {
    command = GlCommand.READ_CURRENT
    this.location = request.gl.toInternal()
    workMode = request.mode.transportToWorkMode()
    stubCase = request.mode.transportToStubCase()
}

fun GeolocationContext.fromTransport(request: IReadAllLocationRequest) {
    command = GlCommand.READ_ALL
    this.location = request.gl.toInternal()
    workMode = request.mode.transportToWorkMode()
    stubCase = request.mode.transportToStubCase()
}

fun GeolocationContext.fromTransport(request: IUpdateLocationRequest) {
    command = GlCommand.UPDATE
    this.location = request.gl.toInternal()
    workMode = request.mode.transportToWorkMode()
    stubCase = request.mode.transportToStubCase()
}

fun GeolocationContext.fromTransport(request: IDeleteLocationRequest) {
    command = GlCommand.DELETE
    this.location = request.gl.toInternal()
    workMode = request.mode.transportToWorkMode()
    stubCase = request.mode.transportToStubCase()
}

private fun WorkMode?.transportToStubCase(): GlStubs = when(this?.stub) {
    RequestStub.SUCCESS -> GlStubs.SUCCESS
    RequestStub.NOT_FOUND -> GlStubs.NO_FOUND
    RequestStub.BAD_ID -> GlStubs.BAD_ID
    RequestStub.BAD_PERSON_ID -> GlStubs.BAD_PERSON_ID
    RequestStub.CANNOT_DELETE -> GlStubs.CANNOT_DELETE
    RequestStub.BAD_SEARCH_STRING -> GlStubs.BAD_SEARCH_STRING
    null -> GlStubs.NONE
}

private fun WorkMode?.transportToWorkMode(): GlWorkMode = when(this?.mode) {
    RequestMode.PROD -> GlWorkMode.PROD
    RequestMode.TEST -> GlWorkMode.TEST
    RequestMode.STUB -> GlWorkMode.STUB
    null -> GlWorkMode.PROD
}

private fun CreateObject.toInternal(): BaseGeolocation =
    BaseGeolocation(
        id = this.id.toId(),
        personId = this.personId.toPersonId(),
        deviceId = this.deviceId.toDeviceId(),
        longitude = this.longitude.toLongitude(),
        latitude = this.latitude.toLatitude(),
        bearing = this.bearing.toBearing(),
        altitude = this.altitude.toAltitude(),
        eventDateTime = this.eventDateTime.toEventDateTime(),
        batteryLevel = this.batteryLevel.toBatteryLevel()
    )

private fun ReadObject?.toInternal(): BaseGeolocation = if (this != null) {
    BaseGeolocation(
        id = id.toId(),
        personId = personId.toPersonId(),
        deviceId = deviceId.toDeviceId()
    )
} else {
    BaseGeolocation()
}

private fun ReadAllObject?.toInternal(): BaseGeolocation = if (this != null) {
    BaseGeolocation(
        id = id.toId(),
        personId = personId.toPersonId(),
        deviceId = deviceId.toDeviceId()
    )
} else {
    BaseGeolocation()
}

private fun UpdateObject?.toInternal(): BaseGeolocation =
    BaseGeolocation(
        id = this?.id.toId(),
        personId = this?.personId.toPersonId(),
        deviceId = this?.deviceId.toDeviceId(),
        longitude = this?.longitude.toLongitude(),
        latitude = this?.latitude.toLatitude(),
        bearing = this?.bearing.toBearing(),
        altitude = this?.altitude.toAltitude(),
        eventDateTime = this?.eventDateTime.toEventDateTime(),
        batteryLevel = this?.batteryLevel.toBatteryLevel()
    )

private fun DeleteObject?.toInternal(): BaseGeolocation =
    BaseGeolocation(
        id = this?.id.toId(),
        personId = this?.personId.toPersonId(),
        deviceId = this?.deviceId.toDeviceId()
    )

private fun Long?.toId() = this?.let { GeolocationId(it) } ?: GeolocationId.NONE
private fun Long?.toPersonId() = this?.let { PersonId(it) } ?: PersonId.NONE
private fun Long?.toDeviceId() = this?.let { DeviceId(it) } ?: DeviceId.NONE
private fun Double?.toLatitude() = this?.let { Latitude(it) } ?: Latitude.NONE
private fun Double?.toLongitude() = this?.let { Longitude(it) } ?: Longitude.NONE
private fun Double?.toBearing() = this?.let { Bearing(it) } ?: Bearing.NONE
private fun Double?.toAltitude() = this?.let { Altitude(it) } ?: Altitude.NONE
private fun String?.toEventDateTime() = this?.let { EventDateTime(it) } ?: EventDateTime.NONE
private fun Float?.toBatteryLevel() = this?.let { BatteryLevel(it) } ?: BatteryLevel.NONE