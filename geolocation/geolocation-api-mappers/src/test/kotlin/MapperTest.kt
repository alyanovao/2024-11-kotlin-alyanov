import ru.aao.geolocation.api.v1.models.*
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.*
import ru.aao.geolocation.common.stubs.GlStubs
import kotlin.test.Test
import kotlin.test.assertEquals

class MapperTest {
    @Test
    fun fromTransport() {
        val request = ICreateLocationRequest(
            mode = WorkMode(
                mode = RequestMode.STUB,
                stub = RequestStub.SUCCESS
            ),
            gl = CreateObject(
                personId = 1,
                deviceId = 1,
                latitude = 45.05,
                longitude = 39.0,
                bearing = 0.0,
                altitude = 48.0,
                batteryLevel = 76.0F
            )
        )
        val context = GeolocationContext()
        context.fromTransport(request)

        assertEquals(GlStubs.SUCCESS, context.stubCase)
        assertEquals(GlWorkMode.STUB, context.workMode)
        assertEquals(DeviceId(1), context.location.deviceId)
        assertEquals(PersonId(1), context.location.personId)
        assertEquals(Latitude(45.05), context.location.latitude)
        assertEquals(Longitude(39.0), context.location.longitude)
        assertEquals(Bearing(0.0), context.location.bearing)
        assertEquals(Altitude(48.0), context.location.altitude)
        assertEquals(BatteryLevel(76.0F), context.location.batteryLevel)
    }

    @Test
    fun toTransport() {
        val context = GeolocationContext(
            requestId = GlRequestId(1),
            command = GlCommand.CREATE,
            glResponse = ru.aao.geolocation.common.models.BaseGeolocation(
                deviceId = DeviceId(1),
                personId = PersonId(1),
                latitude = Latitude(45.05),
                longitude = Longitude(39.0),
                bearing = Bearing(1.0),
                altitude = Altitude(48.0),
                batteryLevel = BatteryLevel(76.0F)
            ),
            errors = mutableListOf(
                GlError(
                code = "1",
                group = "Test",
                field = "comment",
                message = "Not null message"
            )
            ),
            state = GlState.RUNNING
        )
        val request = context.toTransport() as ICreateLocationResponse
        val errors = request.errors.takeIf { it?.isNotEmpty() == true }

        assertEquals(ResponseResult.SUCCESS, request.result)

        for (error in errors.orEmpty()) {
            assertEquals("1", error?.code)
            assertEquals("Test", error?.group)
            assertEquals("comment", error?.field)
            assertEquals("Not null message", error?.message)
        }

        assertEquals(1, request.gl?.deviceId)
        assertEquals(1, request.gl?.personId)
        assertEquals(45.05, request.gl?.latitude)
        assertEquals(39.0, request.gl?.longitude)
        assertEquals(1.0, request.gl?.bearing)
        assertEquals(48.0, request.gl?.altitude)
        assertEquals(76.0F, request.gl?.batteryLevel)
    }
}