package models

@JvmInline
value class GlRequestId(private val id: Long) {
    fun asLong() = id

    companion object {
        val NONE = GlRequestId(0L)
    }
}
