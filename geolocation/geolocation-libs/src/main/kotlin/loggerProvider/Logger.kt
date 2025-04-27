import ch.qos.logback.classic.Logger
import logger.IGlLogWrapper
import loggerProvider.LoggerLogback


fun lgLogger(logger: Logger): IGlLogWrapper = LoggerLogback(
    logger = logger,
    loggerId = logger.name
)