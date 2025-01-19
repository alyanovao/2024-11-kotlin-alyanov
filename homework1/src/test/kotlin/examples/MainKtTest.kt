package examples

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.test.*

class MainKtTest {
    @Test
    fun firstTest() {
        assertEquals(3, 2 + 1)
    }

}

internal class DelegationTest {

    private class DelegateExample {
        val constVal by ConstValue(100501)
        val lazyVal by lazy {
            println("calculate ...")
            42
        }
    }

    @Test
    fun test() {
        val example = DelegateExample()

        println(example.constVal)
        assertEquals(100501, example.constVal)

        println(example.lazyVal)
        assertEquals(42, example.lazyVal)
    }

    private class ConstValue(private val value: Int): ReadWriteProperty<Any?, Int> {
        override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
            return value
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
            throw IllegalArgumentException("ConstValue can't be reassigned")
        }
    }
}