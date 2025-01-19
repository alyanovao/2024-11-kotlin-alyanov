package examples

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class GenericTest {

    @Test
    fun invariant() {
        assertEquals(3, (IntSome(1) + IntSome(2)).value)
    }

    @Test
    fun covariant() {
        assertEquals(3, CovariantCls().parse("3"))
    }

    @Test
    fun contravariant() {
        assertEquals("3", ContravariantCls().toString(3))
    }

    private interface ISome<T : ISome<T>> {
        operator fun plus(other: T): T
    }

    private class IntSome(val value: Int) : ISome<IntSome> {
        override fun plus(other: IntSome): IntSome = IntSome(value + other.value)
    }

    private interface IParse<out T> {
        fun parse(str: String): Int
    }

    private class CovariantCls: IParse<Int> {
        override fun parse(str: String): Int = str.toInt()
    }

    private interface IToString<in T> {
        fun toString(i: T): String
    }

    private class ContravariantCls: IToString<Int> {
        override fun toString(i: Int): String = i.toString()
    }
}