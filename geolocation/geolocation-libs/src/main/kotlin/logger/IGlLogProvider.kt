package logger

interface IGlLogProvider: AutoCloseable, IGlLogWrapper {
    val logId: String

    override fun close() {}
}