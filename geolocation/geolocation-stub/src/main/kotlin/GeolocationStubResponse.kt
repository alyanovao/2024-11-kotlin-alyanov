import models.*

object GeolocationStubResponse {
    val GEOLOCATION: BaseGeolocation
        get() = BaseGeolocation(
            personId = PersonId(1L),
            deviceId = DeviceId(2L),
            latitude = Latitude(45.05),
            longitude = Longitude(39.0),
            bearing = Bearing(1.0),
            altitude = Altitude(48.0),
            batteryLevel = BatteryLevel(76.0F)
        )
}