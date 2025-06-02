package ru.aao.geolocation.repository.inmemory

import ru.aao.geolocation.common.models.*

data class GlEntity(
    val personId: String? = null,
    val deviceId: String? = null,
    val longitude: String? = null,
    var latitude: String? = null,
    var bearing: String? = null,
    val altitude: String? = null,
    val batteryLevel: String? = null
) {
    constructor(model: BaseGeolocation): this(
        personId = model.personId.takeIf { it != PersonId.NONE }.toString(),
        deviceId = model.deviceId.takeIf { it != DeviceId.NONE }.toString(),
        longitude = model.longitude.takeIf { it != Longitude.NONE }.toString(),
        latitude = model.latitude.takeIf { it != Latitude.NONE }.toString(),
        bearing = model.bearing.takeIf { it != Bearing.NONE }.toString(),
        altitude = model.altitude.takeIf{ it != Altitude.NONE }.toString(),
        batteryLevel = model.batteryLevel.takeIf { it != BatteryLevel.NONE }.toString()
    )

    fun toInternal() = BaseGeolocation(
        personId = personId.let { PersonId(convertStringToLong(it)) },
        deviceId = deviceId.let { DeviceId(convertStringToLong(it)) },
        longitude = deviceId.let { Longitude(convertStringToDouble(it)) },
        bearing = bearing.let { Bearing(convertStringToDouble(it)) },
        altitude = altitude.let {Altitude(convertStringToDouble(it))},
        batteryLevel = batteryLevel.let { BatteryLevel(convertStringToFloat(it)) }
    )
}

fun convertStringToLong(message: String?): Long {
    return message?.toLong() ?: 0
}

fun convertStringToDouble(message: String?): Double {
    return message?.toDouble() ?: 0.0
}

fun convertStringToFloat(message: String?): Float {
    return message?.toFloat() ?: 0f
}