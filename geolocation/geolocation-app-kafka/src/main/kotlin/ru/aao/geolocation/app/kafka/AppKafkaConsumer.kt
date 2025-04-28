package ru.aao.geolocation.app.kafka

import kotlinx.atomicfu.atomic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import ru.aao.geolocation.common.ktor.controllerHelper
import java.time.Duration
import java.util.*

class AppKafkaConsumer (
    private val config: AppKafkaConfig,
    consumerStrategies: List<IConsumerStrategy>,
    private val consumer: Consumer<String, String> = config.createKafkaConsumer(),
    private val producer: Producer<String, String> = config.createKafkaProducer()
): AutoCloseable {
    private val log = config.corSettings.logProvider.logger(this::class)
    private val process = atomic(true)
    private val topicAndStrategyByInputTopic: Map<String, TopicAndStrategy> = consumerStrategies.associate {
        val topics = it.topics(config)
        topics.input to TopicAndStrategy(topics.input, topics.output, it)
    }

    fun start(): Unit = runBlocking {startSuspend()}

    suspend fun startSuspend() {
        process.value = true
        try {
            consumer.subscribe(topicAndStrategyByInputTopic.keys)
            while (process.value) {
                val records: ConsumerRecords<String, String> = withContext(Dispatchers.IO) {
                    consumer.poll(Duration.ofSeconds(1))
                }
                if(!records.isEmpty) log.info("Receive ${records.count()} messages")

                records.forEach{record: ConsumerRecord<String, String> ->
                    try {
                        val(_, outTopic, strategy) = topicAndStrategyByInputTopic[record.topic()]
                            ?: throw RuntimeException("Message from unknown topic ${record.topic()}")

                        val resp = config.controllerHelper(
                            {strategy.deserialize(record.value(), this)},
                            {strategy.serialize(this)},
                            KafkaConsumer::class,
                            "kafka-consumer"
                        )
                        sendResponse(resp, outTopic)
                    } catch (e: Exception) {
                        log.error("error", e = e)
                    }
                }
            }
        }
        catch (e: Exception) {
            log.error("Error", e = e)
        }
        finally {
            consumer.close()
        }
    }

    private suspend fun sendResponse(json: String, outTopic: String) {
        val respRecord = ProducerRecord(
            outTopic,
            UUID.randomUUID().toString(),
            json
        )
        log.info("send $outTopic: $respRecord")
        withContext(Dispatchers.IO) {
            producer.send(respRecord)
        }
    }
    override fun close() {
        TODO("Not yet implemented")
    }

    private data class TopicAndStrategy(
        val inputTopic: String,
        val outputTopic: String,
        val strategy: IConsumerStrategy
    )
}