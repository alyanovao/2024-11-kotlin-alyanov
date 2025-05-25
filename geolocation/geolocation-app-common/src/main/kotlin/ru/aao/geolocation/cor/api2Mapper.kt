package ru.aao.geolocation.cor

import kotlinx.serialization.json.Json
import ru.aao.geolocation.api.v1.models.*

val api2Mapper = Json{}

fun <T: IRequest> api2RequestDeserialize(json: String) =
    api2Mapper.decodeFromString<IRequest>(json) as T

//fun api2RequestSerialize(obj: IResponse): String = api2Mapper.encodeToString(IResponse.serializer(), obj)

fun <T: IResponse> api2ResponseDeserialize(json: String) = api2Mapper.decodeFromString<IResponse>(json) as T

//fun apiRequestSerialize(obj: IRequest): String = api2Mapper.encodeToString(IRequest.serializer(), obj)