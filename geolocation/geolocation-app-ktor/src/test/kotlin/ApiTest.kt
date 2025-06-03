package ru.aao.geolocation.common.ktor

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.testing.*
import ru.aao.geolocation.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApiTest {

    @Test
    fun create() = v1TestApplication(
        func = "create",
        request = ICreateLocationRequest(
            gl = CreateObject(
                personId = 1L,
                deviceId = 2L,
                latitude = 1.0
            ),
            mode = WorkMode(
                mode = RequestMode.STUB,
                stub = RequestStub.SUCCESS
            )
        )
    ) { response ->
        val res = response.body<ICreateLocationResponse>()
        assertEquals(200, response.status.value)
        assertEquals(2L, res.gl?.deviceId)
    }

    @Test
    fun readCurrent() = v1TestApplication(
        func = "read/current",
        request = IReadCurrentLocationRequest(
            requestType = "read",
            mode = WorkMode(
                mode = RequestMode.STUB,
                stub = RequestStub.SUCCESS
            )
        )
    ) { response ->
        val res = response.body<IReadCurrentLocationResponse>()
        assertEquals(200, response.status.value)
        assertEquals(2L, res.gl?.deviceId)
        assertEquals("success", res.result?.value)
    }

    private fun v1TestApplication(
        func: String,
        request: IRequest,
        function: suspend (HttpResponse) -> Unit
    ): Unit = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                jackson {
                    disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    enable(SerializationFeature.INDENT_OUTPUT)
                    writerWithDefaultPrettyPrinter()
                }
            }
        }
        val response = client.post("/api/$func") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        function(response)
    }
}