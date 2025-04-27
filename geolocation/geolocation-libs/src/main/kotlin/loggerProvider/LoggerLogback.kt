package loggerProvider

import ch.qos.logback.classic.Logger
import logger.CustomMarker
import logger.IGlLogWrapper
import logger.LogLevel
import org.slf4j.Marker
import org.slf4j.event.KeyValuePair
import org.slf4j.event.Level
import org.slf4j.event.LoggingEvent
import java.time.Instant

class LoggerLogback(
    val logger: Logger,
    override val loggerId: String = logger.name
): IGlLogWrapper {
    private fun log(
        message: String = "",
        level: Level = Level.INFO,
        marker: Marker? = null,
        e: Throwable? = null,
        data: Any? = null,
        objs: Map<String, Any>? = null
    ) {
        logger.log(
            object: LoggingEvent {
                override fun getThrowable() = e
                override fun getTimeStamp(): Long = Instant.now().toEpochMilli()
                override fun getThreadName(): String = Thread.currentThread().name
                override fun getMessage(): String = message
                override fun getArguments(): MutableList<Any> = argumentArray.toMutableList()
                override fun getArgumentArray(): Array<out Any> = data as Array<out Any>
                override fun getMarkers(): MutableList<Marker?> = mutableListOf(marker)
                override fun getKeyValuePairs(): MutableList<KeyValuePair> = objs?.mapNotNull { it.let{KeyValuePair(it.key, it.value)} }
                    ?.toMutableList()
                    ?: mutableListOf()
                override fun getLevel(): Level = level
                override fun getLoggerName(): String = logger.name
            }
     )
    }

    override fun log(
        message: String,
        level: LogLevel,
        marker: String,
        e: Throwable?,
        data: Any?,
        obj: Map<String, Any>?
    ) = log(message, Level.INFO, CustomMarker(), e, data, obj)
}