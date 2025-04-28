package ru.aao.geolocation.app.kafka

import ru.aao.geolocation.common.GeolocationContext
import apiV1RequestDeserialize
import apiV1ResponseSerialize
import fromTransport
import ru.aao.geolocation.api.v1.models.IRequest
import ru.aao.geolocation.api.v1.models.IResponse
import toTransport

class ConsumerStrategyFirst: IConsumerStrategy {
    override fun topics(config: AppKafkaConfig): InputOutputTopics {
        return InputOutputTopics(config.topicIn, config.topicOut)
    }

    override fun serialize(source: GeolocationContext): String {
        val response: IResponse = source.toTransport()
        return apiV1ResponseSerialize(response)
    }

    override fun deserialize(value: String, target: GeolocationContext) {
        val request: IRequest = apiV1RequestDeserialize(value)
        target.fromTransport(request)
    }
}