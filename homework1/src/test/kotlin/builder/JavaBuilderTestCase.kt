package builder

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class JavaBuilderTestCase {

    @Test
    fun test() {
        val breakfast = BreakfastBuilder()
            .withEggs(1)
            .withBacon(true)
            .withDrink(Drink.COFFEE)
            .withTitle("Simple breakfast")
            .build()

        assertEquals(1, breakfast.eggs)
        assertEquals(true, breakfast.bacon)
        assertEquals(Drink.COFFEE, breakfast.drink)
        assertEquals("Simple breakfast", breakfast.title)
    }

    private enum class Drink {
        WATER,
        COFFEE,
        TEA,
        NONE
    }

    private abstract class Meal {
        data class Breakfast(
            val eggs: Int,
            val bacon: Boolean,
            val drink: Drink,
            val title: String
        ) : Meal()

        data class Dinner(
            val title: String
        ) : Meal()
    }

    private class BreakfastBuilder {
        var eggs = 0
        var bacon = false
        var drink = Drink.NONE
        var title = ""

        fun withEggs(eggs: Int): BreakfastBuilder {
            this.eggs = eggs
            return this
        }

        fun withBacon(bacon: Boolean): BreakfastBuilder {
            this.bacon = bacon
            return this
        }

        fun withDrink(drink: Drink): BreakfastBuilder {
            this.drink = drink
            return this
        }

        fun withTitle(title: String): BreakfastBuilder {
            this.title = title
            return this
        }

        fun build() = Meal.Breakfast(eggs, bacon, drink, title)
    }
}