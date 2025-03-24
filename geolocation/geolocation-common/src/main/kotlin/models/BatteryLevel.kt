package models

@JvmInline
value class BatteryLevel(private val batteryLevel: Float) {
    fun asFloat(): Float = batteryLevel
    companion object {
        val NONE = BatteryLevel(0F)
    }
}