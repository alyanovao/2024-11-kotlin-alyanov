package examples

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

internal class DelegationTest1 {

    interface IDelegate {
        fun x(): String
        fun y(): String
    }

    private class MyClass: IDelegate {
        override fun x(): String {
            println("MyClass.x()")
            return "x"
        }

        override fun y(): String = "y"
            .also { println("MyClass.y()")}
    }

    private class MyDelegate(
        private val del: IDelegate
    ): IDelegate by del {
        override fun x(): String {
            println("Calling x()")
            val subString = del.x()
            println("Calling x() done")
            return "delegate for ($subString)"
        }
    }

    @Test
    fun delegate() {
        val base = MyClass()
        val delegate = MyDelegate(base)

        println("Calling base")
        assertEquals("x", base.x())
        assertEquals("y", base.y())

        println("Calling delegate")
        assertEquals("delegate for (x)", delegate.x())
        assertEquals("y", delegate.y())
    }
}