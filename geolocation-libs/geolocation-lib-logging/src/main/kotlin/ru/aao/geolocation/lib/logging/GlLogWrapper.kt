package ru.aao.geolocation.lib.logging

interface IGlLogWrapper: AutoCloseable {
    val loggerId: String

    fun log(
        msg: String = "",
        level: LogLevel = LogLevel.INFO,
        marker: String = "",
        e: Throwable? = null,
        data: Any? = null,
        obj: Map<String,  Any>? = null
    )

    fun error(
        msg: String = "",
        level: LogLevel = LogLevel.ERROR,
        marker: String = "",
        e: Throwable? = null,
        data: Any? = null,
        obj: Map<String,  Any>? = null
    ) = log(msg, level, marker, e, data)

    fun info(
        msg: String = "",
        level: LogLevel = LogLevel.INFO,
        marker: String = "",
        e: Throwable? = null,
        data: Any? = null,
        obj: Map<String,  Any>? = null
    ) = log(msg, level, marker, e, data)

    override fun close() {}

    companion object {
        val DEFAULT = object: IGlLogWrapper {
            override val loggerId: String = "NONE"

            override fun log(
                msg: String,
                level: LogLevel,
                marker: String,
                e: Throwable?,
                data: Any?,
                obj: Map<String, Any>?
            ) {
                println("$level $marker $data")
            }

            override fun error(
                msg: String,
                level: LogLevel,
                marker: String,
                e: Throwable?,
                data: Any?,
                obj: Map<String, Any>?
            ) {
                println("$level $marker $data")
            }
        }
    }
}