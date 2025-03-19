package models

@JvmInline
value class GlLock(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = GlLock("")
    }
}