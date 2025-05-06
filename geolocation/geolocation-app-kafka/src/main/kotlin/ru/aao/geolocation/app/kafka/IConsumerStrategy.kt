package ru.aao.geolocation.app.kafka

import ru.aao.geolocation.common.GeolocationContext

interface IConsumerStrategy {
    fun topics(config: AppKafkaConfig): InputOutputTopics
    fun serialize(source: GeolocationContext): String
    fun deserialize(value: String, target: GeolocationContext)
}