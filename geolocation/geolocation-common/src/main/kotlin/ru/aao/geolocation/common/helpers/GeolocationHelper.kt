package ru.aao.geolocation.common.helpers

import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlError
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.lib.logging.LogLevel

inline fun GeolocationContext.addError(error: GlError) = errors.add(error)
inline fun GeolocationContext.addErrors(error: Collection<GlError>) = errors.addAll(error)

inline fun GeolocationContext.fail(error: GlError) {
    addError(error)
    state = GlState.FAILING
}

inline fun GeolocationContext.fail(error: Collection<GlError>) {
    addErrors(error)
    state = GlState.FAILING
}

inline fun errorSystem(
    violationCode: String,
    level: LogLevel = LogLevel.ERROR,
    e: Throwable
) = GlError(
    code = "system-$violationCode",
    group = "System",
    message = "System error",
    level = level,
    exception = e
)