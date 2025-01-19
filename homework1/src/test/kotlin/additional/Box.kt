package additional

class Box<T> (val value: T)
class BoxCovariant<out T> (val value: T)
class BoxContravariant<in T> {
    fun put(value: T) {}
}

fun main() {
    val intBox: Box<Int> = Box(100)
    val intBoxCovariant: BoxCovariant<Int> = BoxCovariant(123)
    val anyBoxCovariant: BoxCovariant<Any> = intBoxCovariant
    val anyBoxContravariant: BoxContravariant<Any> = BoxContravariant()
    val intBoxContravariant: BoxContravariant<Int> = anyBoxContravariant
}
