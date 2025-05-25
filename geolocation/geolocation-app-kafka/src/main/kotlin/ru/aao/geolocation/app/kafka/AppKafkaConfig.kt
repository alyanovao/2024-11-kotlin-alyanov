package ru.aao.geolocation.app.kafka

import ru.aao.geolocation.biz.GlProcessor
import ru.aao.geolocation.cor.IGlAppSettings
import ru.aao.geolocation.common.GlSettings

class AppKafkaConfig (
    val kafkaHosts: List<String> = KAFKA_HOSTS,
    val kafkaGroupId: String = KAFKA_GROUP_ID,
    val topicIn: String = KAFKA_TOPIC_IN,
    val topicOut: String = KAFKA_TOPIC_OUT,
    override val corSettings: GlSettings = GlSettings(),
    override val processor: GlProcessor = GlProcessor(corSettings),
): IGlAppSettings {
    companion object {
        const val KAFKA_HOSTS_VAR = "KAFKA_HOSTS"
        const val KAFKA_GROUP_ID_VAR = "KAFKA_GROUP_ID"
        const val KAFKA_TOPIC_IN_VAR = "KAFKA_TOPIC_IN"
        const val KAFKA_TOPIC_OUT_VAR = "KAFKA_TOPIC_OUT"

        val KAFKA_HOSTS by lazy {
            println("! KAFKA_HOSTS = ${System.getenv(KAFKA_HOSTS_VAR)}")
            (System.getenv(KAFKA_HOSTS_VAR) ?: "localhost:9092").split("\\s*[;]\\s*")}
        val KAFKA_GROUP_ID by lazy {System.getenv(KAFKA_GROUP_ID_VAR) ?: "kafka-group-id"}
        val KAFKA_TOPIC_IN by lazy {System.getenv(KAFKA_TOPIC_IN_VAR) ?: "kafka-topic-in"}
        val KAFKA_TOPIC_OUT by lazy {System.getenv(KAFKA_TOPIC_OUT_VAR) ?: "kafka-topic-out"}
    }
}