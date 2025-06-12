package ru.aao.geolocation.repository.postgres

import ru.aao.geolocation.common.models.GlError

fun Throwable.asGlError(
    code: String = "",
    group: String = "",
    message: String = ""
) = GlError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this
)