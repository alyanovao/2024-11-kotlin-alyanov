import ru.aao.geolocation.api.v1.models.CreateObject
import ru.aao.geolocation.api.v1.models.DeleteObject
import ru.aao.geolocation.api.v1.models.ReadObject
import ru.aao.geolocation.api.v1.models.UpdateObject
import ru.aao.geolocation.common.models.*


fun BaseGeolocation.toTransportCreate() = CreateObject(
    id = id.takeIf { it != GeolocationId.NONE }?.asLong(),
    personId = personId.takeIf { it != PersonId.NONE }?.asLong(),
    deviceId = deviceId.takeIf { it != DeviceId.NONE }?.asLong(),
    longitude = longitude.takeIf { it != Longitude.NONE }?.asDouble(),
    latitude = latitude.takeIf { it != Latitude.NONE }?.asDouble(),
    bearing = bearing.takeIf { it != Bearing.NONE }?.asDouble(),
    altitude = altitude.takeIf { it != Altitude.NONE }?.asDouble(),
    batteryLevel = batteryLevel.takeIf { it != BatteryLevel.NONE }?.asFloat()
)

fun BaseGeolocation.toTransportReadCurrent() = ReadObject(
    id = id.takeIf { it != GeolocationId.NONE }?.asLong(),
    personId = personId.takeIf { it != PersonId.NONE }?.asLong()
)

fun BaseGeolocation.toTransportReadAllCurrent() = ReadObject(
    id = id.takeIf { it != GeolocationId.NONE }?.asLong(),
    personId = personId.takeIf { it != PersonId.NONE }?.asLong()
)

fun BaseGeolocation.toTransportUpdate() = UpdateObject(
    id = id.takeIf { it != GeolocationId.NONE }?.asLong(),
    personId = personId.takeIf { it != PersonId.NONE }?.asLong(),
    deviceId = deviceId.takeIf { it != DeviceId.NONE }?.asLong(),
    longitude = longitude.takeIf { it != Longitude.NONE }?.asDouble(),
    latitude = latitude.takeIf { it != Latitude.NONE }?.asDouble(),
    bearing = bearing.takeIf { it != Bearing.NONE }?.asDouble(),
    altitude = altitude.takeIf { it != Altitude.NONE }?.asDouble(),
    batteryLevel = batteryLevel.takeIf { it != BatteryLevel.NONE }?.asFloat()
)

fun BaseGeolocation.toTransportDelete() = DeleteObject(
    id = id.takeIf { it != GeolocationId.NONE }?.asLong(),
    personId = personId.takeIf { it != PersonId.NONE }?.asLong(),
    deviceId = deviceId.takeIf { it != DeviceId.NONE }?.asLong()
)