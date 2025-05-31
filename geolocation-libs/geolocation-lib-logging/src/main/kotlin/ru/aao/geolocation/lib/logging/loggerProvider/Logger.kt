package ru.aao.geolocation.lib.logging.loggerProvider

import ch.qos.logback.classic.Logger
import ru.aao.geolocation.lib.logging.IGlLogWrapper


fun lgLogger(logger: Logger): IGlLogWrapper = LoggerLogback(
    logger = logger,
    loggerId = logger.name
)