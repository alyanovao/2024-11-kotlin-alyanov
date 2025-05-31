import ru.aao.geolocation.common.models.BaseGeolocation

object GeolocationStub {
    fun prepareResult(block: BaseGeolocation.() -> Unit) = get().apply(block)
    fun get(): BaseGeolocation = GeolocationStubResponse.GEOLOCATION
}