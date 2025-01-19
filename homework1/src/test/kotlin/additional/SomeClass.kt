package additional

class SomeClass {
    companion object {
        const val PI = 3.1415

        fun build(): SomeClass {
            return SomeClass()
        }
    }
}

fun main() {
    println(SomeClass.PI)
    println(SomeClass.Companion.PI)
    val a = SomeClass.PI
    val b = SomeClass.PI
    println(a === b)

}