import ru.aao.geolocation.common.models.BaseGeolocation

object GeolocationStub {
    fun get(): BaseGeolocation = GeolocationStubResponse.GEOLOCATION
}