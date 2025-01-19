import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class SimpleTestCase {

    @Test
    fun mini() {
        val result = sout {
            1 + 123
        }
        assertEquals("string 124", result)
    }

    private fun sout(block: () -> Int?): String {
        val result = block()
        println(result)
        return "string $result"
    }

    @Test
    fun soutWithPrefixTest() {
        soutWithContext {
            "${time()}: my life"
        }
    }

    class MyContext {
        fun time() = System.currentTimeMillis()
    }

    private fun soutWithContext(block: MyContext.() -> Any?) {
        val context = MyContext()
        val result = block(context)
        println(result)
    }

    @Test
    fun test() {
        val (key, value) = Pair("key", "value")
        assertEquals("key", key)
        assertEquals("value", value)

        val keyPair = "key" to "value"
        assertEquals(keyPair.first, key)
        assertEquals(keyPair.second, value)

        val myTimeOld = "12".time("30")
        assertEquals(myTimeOld, "12:30")

        val myTime = "12" time "30"
        assertEquals(myTime, "12:30")
    }

    private infix fun String.time(value: String): String {
        return "$this:$value"
    }
}