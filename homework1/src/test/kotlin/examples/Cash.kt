package examples

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class Cash(
    val amount: BigDecimal,
    private val currency: Currency
) {
    constructor(
        amount: String,
        currency: Currency
    ) : this(BigDecimal(amount), currency)

    fun format(locate: Locale): String {
        val formatter = NumberFormat.getCurrencyInstance(locate)
        formatter.currency = currency
        return formatter.format(amount)
    }

    operator fun plus(other: Cash): Cash {
        require(currency == other.currency) {
            "Summ should be of the same currency"
        }
        return Cash(amount + other.amount, currency)
    }

    operator fun minus(other: Cash): Cash {
        require(currency == other.currency) {
            "Summ should be of the same currency"
        }
        return Cash(amount - other.amount, currency)
    }

    companion object {
        val NONE = Cash(BigDecimal.ZERO, Currency.getInstance("RUR"))
    }
}

interface IClass {}

abstract class BaseClass() {}

class InheritedClass(
    arg: String,
    val prop: String = arg
) : IClass, BaseClass() {
    init {
        println("Init constructor with $arg")
    }

    fun some() {
        this.prop
    }
}

class CashTest {

    @Test
    fun test() {
        val a = Cash("10", Currency.getInstance("USD"))
        val b = Cash("20", Currency.getInstance("USD"))

        val c = b - a
        println(c.amount)

        println(a)

        println(c.format(Locale.FRANCE))

        val d = a + b
        println(d.amount)
        println(d.format(Locale.ENGLISH))

        assertEquals(c.amount, BigDecimal.TEN)

        assertEquals(Cash.NONE, Cash.NONE)

    }
}