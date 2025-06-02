import kotlinx.coroutines.test.runTest
import ru.aao.geolocation.biz.GlProcessor
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.*
import kotlin.test.Test
import kotlin.test.assertEquals

class StubTest {
    private val processor: GlProcessor = GlProcessor()

    @Test
    fun create(): Unit = runTest {
        val ctx = GeolocationContext(
            requestId = GlRequestId(1),
            command = GlCommand.CREATE,
            glResponse = BaseGeolocation(
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
        processor.exec(ctx)
        assertEquals(DeviceId(1), ctx.glResponse.deviceId)
        assertEquals(PersonId(1), ctx.glResponse.personId)
        assertEquals(Latitude(45.05), ctx.glResponse.latitude)
        assertEquals(Longitude(39.0), ctx.glResponse.longitude)
        assertEquals(Bearing(1.0), ctx.glResponse.bearing)
        assertEquals(Altitude(48.0), ctx.glResponse.altitude)
        assertEquals(BatteryLevel(76.0F), ctx.glResponse.batteryLevel)
    }

    @Test
    fun readCurrent(): Unit = runTest {
        val ctx = GeolocationContext(
            requestId = GlRequestId(1),
            command = GlCommand.READ_CURRENT,
            glResponse = BaseGeolocation(
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
        processor.exec(ctx)
        assertEquals(DeviceId(1), ctx.glResponse.deviceId)
        assertEquals(PersonId(1), ctx.glResponse.personId)
        assertEquals(Latitude(45.05), ctx.glResponse.latitude)
        assertEquals(Longitude(39.0), ctx.glResponse.longitude)
        assertEquals(Bearing(1.0), ctx.glResponse.bearing)
        assertEquals(Altitude(48.0), ctx.glResponse.altitude)
        assertEquals(BatteryLevel(76.0F), ctx.glResponse.batteryLevel)
    }

    @Test
    fun readAll(): Unit = runTest {
        val ctx = GeolocationContext(
            requestId = GlRequestId(1),
            command = GlCommand.READ_ALL,
            glResponse = BaseGeolocation(
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
        processor.exec(ctx)
        assertEquals(DeviceId(1), ctx.glResponse.deviceId)
        assertEquals(PersonId(1), ctx.glResponse.personId)
        assertEquals(Latitude(45.05), ctx.glResponse.latitude)
        assertEquals(Longitude(39.0), ctx.glResponse.longitude)
        assertEquals(Bearing(1.0), ctx.glResponse.bearing)
        assertEquals(Altitude(48.0), ctx.glResponse.altitude)
        assertEquals(BatteryLevel(76.0F), ctx.glResponse.batteryLevel)
    }

    @Test
    fun update(): Unit = runTest {
        val ctx = GeolocationContext(
            requestId = GlRequestId(1),
            command = GlCommand.UPDATE,
            glResponse = BaseGeolocation(
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
        processor.exec(ctx)
        assertEquals(DeviceId(1), ctx.glResponse.deviceId)
        assertEquals(PersonId(1), ctx.glResponse.personId)
        assertEquals(Latitude(45.05), ctx.glResponse.latitude)
        assertEquals(Longitude(39.0), ctx.glResponse.longitude)
        assertEquals(Bearing(1.0), ctx.glResponse.bearing)
        assertEquals(Altitude(48.0), ctx.glResponse.altitude)
        assertEquals(BatteryLevel(76.0F), ctx.glResponse.batteryLevel)
    }

    @Test
    fun delete(): Unit = runTest {
        val ctx = GeolocationContext(
            requestId = GlRequestId(1),
            command = GlCommand.DELETE,
            glResponse = BaseGeolocation(
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
        processor.exec(ctx)
        assertEquals(DeviceId(1), ctx.glResponse.deviceId)
        assertEquals(PersonId(1), ctx.glResponse.personId)
        assertEquals(Latitude(45.05), ctx.glResponse.latitude)
        assertEquals(Longitude(39.0), ctx.glResponse.longitude)
        assertEquals(Bearing(1.0), ctx.glResponse.bearing)
        assertEquals(Altitude(48.0), ctx.glResponse.altitude)
        assertEquals(BatteryLevel(76.0F), ctx.glResponse.batteryLevel)
    }
}