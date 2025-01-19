package additional

@JvmInline
value class Age(val value: Int) {
    fun isAdult() = value >= 18
}

fun main() {
    val age = Age(18)
    println("Age: ${age.value}")
    println("Is adult: ${age.isAdult()}")
}