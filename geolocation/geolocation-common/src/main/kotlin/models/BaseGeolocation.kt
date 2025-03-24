package models

data class BaseGeolocation(
    var personId: PersonId = PersonId.NONE,
    var deviceId: DeviceId = DeviceId.NONE,
    var longitude: Longitude = Longitude.NONE,
    var latitude: Latitude = Latitude.NONE,
    var bearing: Bearing = Bearing.NONE,
    var altitude: Altitude = Altitude.NONE,
    var eventDateTime: EventDateTime = EventDateTime.NONE,
    var batteryLevel: BatteryLevel = BatteryLevel.NONE,
    var lock: GlLock = GlLock.NONE,
    var permissionClient: MutableSet<GlPermissionClient> = mutableSetOf()
) {
    fun isEmpty() = this == NONE

    companion object {
        private val NONE = BaseGeolocation()
    }
}