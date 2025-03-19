package models

@JvmInline
value class Altitude(private val value: Double) {
    fun asDouble() = value

    companion object {
        val NONE = Altitude(0.0)
    }
}