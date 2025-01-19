package additional

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class Cash constructor (
    val amount: BigDecimal,
    val currency: Currency,
    argument: String
    )
{
    init {
        println(argument)
    }
}

class Cash2(
    val amount: BigDecimal,
    val currency: Currency,
    argument: String
)

class Cash3(
    val amount: BigDecimal,
    val currency: Currency = Currency.getInstance("RUR"),
    argument: String
)

class Cash4 private constructor (
    val amount: BigDecimal,
)

class Cash5 (
    val amount: BigDecimal,
    val currency: Currency,
    argument: String
) {
    constructor (
        amount: BigDecimal,
        currency: String
    ): this(amount, Currency.getInstance(currency), currency)
}

class Cash6(
    val amount: BigDecimal,
    val currency: Currency
) {
    fun format(locale: Locale): String {
        val formatter = NumberFormat.getCurrencyInstance(locale)
        formatter.currency = currency
        return formatter.format(amount)
    }
}

class Cash7 (
    val amount: BigDecimal,
    val currency: Currency
) {
    operator fun minus(cash: Cash7): Cash7 {
        require(currency == cash.currency) {
            "Different currencies"
        }
        return Cash7(amount - cash.amount, currency)
    }

    operator fun plus(cash: Cash7): Cash7 {
        require(currency == cash.currency) {
            "Different currencies"
        }
        return Cash7(amount + cash.amount, currency)
    }
}

abstract class AbstractBase {

    abstract val prop: String

    abstract fun method(): Double

    val property: String = ""

    fun method1() {
        println("AbstractBase")
    }
}

class ConcreteClass: AbstractBase() {
    override val prop = "ConcreteClass"

    override fun method(): Double {
        return 42.0
    }

    fun additionalMethod() {
        println("Additional method")
    }
}

fun main() {
    val cash = Cash(
        BigDecimal.valueOf(100),
        Currency.getInstance("USD"),
        "Class cash"
    )

    val cash2 = Cash2 (
        100.toBigDecimal(),
        Currency.getInstance("RUR"),
        ""
    )
    println(cash.toString())
    println(cash2.toString())
    val cash3 = Cash3 (BigDecimal.TEN, argument = "")
    println(cash3.amount.toString() + " :: " + cash3.currency)
    val cash5 = Cash5(BigDecimal(100), "RUR")
    val cash6 = Cash6(BigDecimal(100), Currency.getInstance("USD"))
    println("${cash6.format(Locale.ENGLISH)}")
    val sum1 = Cash7(BigDecimal(100), Currency.getInstance("RUR"))
    val sum2 = Cash7(BigDecimal(50), Currency.getInstance("RUR"))
    val sum3 = Cash7(BigDecimal(70), Currency.getInstance("RUR"))
    val sum = sum1 - sum2 + sum3
    println(sum.amount)
    println()

    val instance = ConcreteClass()
    println(instance.prop)
    println(instance.method())
    println(instance.property)
    instance.method1()
    instance.additionalMethod()

}