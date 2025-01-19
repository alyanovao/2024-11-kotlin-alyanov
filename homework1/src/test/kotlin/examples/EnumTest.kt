package examples

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

enum class HightLowEnum {
    LOW,
    HIGH
}

enum class HighLowWithData(val level: Int, val description: String) {
    LOW(10, "low level"),
    HIGH(20, "high level")
}

enum class FooBarEnum: Iterable<FooBarEnum> {
    FOO {
        override fun doSome() {
            println("do foo")
        }
    },
    BAR {
        override fun doSome() {
            println("do bar")
        }
    };

    abstract fun doSome();
    override fun iterator(): Iterator<FooBarEnum> = listOf(FOO, BAR).iterator()
}

class EnumTest {

    @Test
    fun enum() {
        var e = HightLowEnum.LOW
        println(e)

        e = HightLowEnum.valueOf("HIGH")
        println(e)
        println(e.ordinal)
        assertEquals(1, e.ordinal)
        println(HightLowEnum.entries.joinToString())
    }

    @Test
    fun enumWithData() {
        var e = HighLowWithData.LOW
        println(e)
        println(e.ordinal)

        e = HighLowWithData.valueOf("HIGH")
        println(e)
        println(e.ordinal)
        assertEquals(1, e.ordinal)
        assertEquals(20, e.level)

        println(HighLowWithData.entries.joinToString())
    }

    @Test
    fun interfacedEnums() {
        assertEquals(listOf(FooBarEnum.FOO, FooBarEnum.BAR), FooBarEnum.BAR.iterator().asSequence().toList())
        assertEquals(listOf(FooBarEnum.FOO, FooBarEnum.BAR), FooBarEnum.FOO.iterator().asSequence().toList())
    }

    @Test
    fun enumFailures() {
        assertFails {
            HightLowEnum.valueOf("high")
        }

        val s = kotlin.runCatching {
            HightLowEnum.valueOf("high")
        }.getOrDefault(HightLowEnum.HIGH)
        assertEquals(HightLowEnum.HIGH, s)
    }
}