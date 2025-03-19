import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import ru.aao.geolocation.api.v1.models.IRequest
import ru.aao.geolocation.api.v1.models.IResponse

val apiMapper: JsonMapper = JsonMapper.builder().run {
    enable(MapperFeature.USE_BASE_TYPE_AS_DEFAULT_IMPL)
    build()
}

fun apiV1RequestSerialize(request: IRequest): String = apiMapper.writeValueAsString(request)

fun <T : IRequest> apiV1RequestDeserialize(json: String): T =
    apiMapper.readValue(json, IRequest::class.java) as T

fun apiV1ResponseSerialize(response: IResponse): String = apiMapper.writeValueAsString(response)

fun <T : IResponse> apiV1ResponseDeserialize(json: String): T =
    apiMapper.readValue(json, IResponse::class.java) as T
