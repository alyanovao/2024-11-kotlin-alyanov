package ru.aao.geolocationapimappers

import apiMapper
import ru.aao.geolocation.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class SerializationTest {
    private val request = ICreateLocationRequest(
        requestType = "create",
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

    @Test
    fun serialize() {
        val json = apiMapper.writeValueAsString(request)
        assertContains(json, "\"personId\":1")
        assertContains(json, "\"deviceId\":1")
        assertContains(json, "\"latitude\":45.05")
        assertContains(json, "\"longitude\":39.0")
        assertContains(json, "\"bearing\":0.0")
        assertContains(json, "\"altitude\":48.0")
        assertContains(json, "\"batteryLevel\":76.0")
    }

    @Test
    fun deserialize() {
        val json = apiMapper.writeValueAsString(request)
        val obj = apiMapper.readValue(json, IRequest::class.java) as ICreateLocationRequest
        assertEquals(request, obj)
    }
}