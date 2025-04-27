import models.BaseGeolocation

object GeolocationStub {
    fun get(): BaseGeolocation = GeolocationStubResponse.GEOLOCATION
}