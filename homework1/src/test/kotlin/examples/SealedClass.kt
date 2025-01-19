package examples

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

sealed interface Base

data object ChildA: Base

class ChildB: Base {
    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}

class SealedTest {

    @Test
    fun test() {
        val obj: Base = ChildA

        val result = when (obj) {
            is ChildA -> "a"
            is ChildB -> "b"
        }

        println(result)
        assertEquals("a", result)
    }
}