package ru.aao.geolocation.repository.inmemory

import ru.aao.geolocation.common.models.*

data class GlEntity(
    val id : String? = null,
    val personId: String? = null,
    val deviceId: String? = null,
    val longitude: String? = null,
    var latitude: String? = null,
    var bearing: String? = null,
    val altitude: String? = null,
    val batteryLevel: String? = null
) {
    constructor(model: BaseGeolocation): this(
        id = model.id.asLong().toString(),
        personId = model.personId.asLong().toString(),
        deviceId = model.deviceId.asLong().toString(),
        longitude = model.longitude.asDouble().toString(),
        latitude = model.latitude.asDouble().toString(),
        bearing = model.bearing.asDouble().toString(),
        altitude = model.altitude.asDouble().toString(),
        batteryLevel = model.batteryLevel.asFloat().toString()
    )

    fun toInternal() = BaseGeolocation(
        id = id?.let { GeolocationId(it.toLong()) }?: GeolocationId.NONE,
        personId = personId?.let { PersonId(it.toLong()) }?: PersonId.NONE,
        deviceId = deviceId?.let { DeviceId(it.toLong()) }?: DeviceId.NONE,
        latitude = latitude?.let { Latitude(it.toDouble()) }?: Latitude.NONE,
        longitude = longitude?.let { Longitude(it.toDouble()) }?: Longitude.NONE,
        bearing = bearing?.let { Bearing(it.toDouble()) }?: Bearing.NONE,
        altitude = altitude?.let {Altitude(it.toDouble())}?: Altitude.NONE,
        batteryLevel = batteryLevel?.let { BatteryLevel(it.toFloat()) }?: BatteryLevel.NONE
    )
}