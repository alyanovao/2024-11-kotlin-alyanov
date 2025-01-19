package additional

interface Base {
    fun print()
}

class BaseImpl(val message: Int): Base {
    override fun print() {
        println(message)
    }
}

class Delivered(b: Base): Base by b

fun main() {
    val b = BaseImpl(1000)
    val delivered = Delivered(b)
    delivered.print()
}