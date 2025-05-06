package ru.aao.geolocation.app.kafka

fun main() {
    val config = AppKafkaConfig()
    val consumer = AppKafkaConsumer(config, listOf(ConsumerStrategyFirst()))
    consumer.start()
}