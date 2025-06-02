import ru.aao.geolocation.api.v1.models.CreateObject
import ru.aao.geolocation.api.v1.models.DeleteObject
import ru.aao.geolocation.api.v1.models.ReadObject
import ru.aao.geolocation.api.v1.models.UpdateObject
import ru.aao.geolocation.common.models.*


fun BaseGeolocation.toTransportCreate() = CreateObject(
    personId = personId.takeIf { it != PersonId.NONE }?.asLong(),
    deviceId = deviceId.takeIf { it != DeviceId.NONE }?.asLong(),
    latitude = latitude.takeIf { it != Latitude.NONE }?.asDouble(),
    longitude = longitude.takeIf { it != Longitude.NONE }?.asDouble(),
    altitude = altitude.takeIf { it != Altitude.NONE }?.asDouble()
)

fun BaseGeolocation.toTransportReadCurrent() = ReadObject(
    personId = personId.takeIf { it != PersonId.NONE }?.asLong()
)

fun BaseGeolocation.toTransportUpdate() = UpdateObject(
    personId = personId.takeIf { it != PersonId.NONE }?.asLong(),
    deviceId = deviceId.takeIf { it != DeviceId.NONE }?.asLong()
)

fun BaseGeolocation.toTransportDelete() = DeleteObject(
    personId = personId.takeIf { it != PersonId.NONE }?.asLong(),
    deviceId = deviceId.takeIf { it != DeviceId.NONE }?.asLong()
)