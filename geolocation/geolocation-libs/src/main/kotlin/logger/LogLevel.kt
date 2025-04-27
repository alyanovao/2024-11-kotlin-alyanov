package logger

enum class LogLevel (
    private val level: Int,
    private val levelStr: String
) {
    ERROR(40, "ERROR"),
    WARN(30, "WARN"),
    INFO(20, "INFO"),
    DEBUG(10, "DEBUG"),
    TRACE(0, "TRACE");

    fun toInt(): Int {
        return level
    }

    override fun toString(): String {
        return levelStr
    }
}