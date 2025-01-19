package builder

import kotlin.test.Test
import kotlin.test.assertEquals

class KotlinBuilderTestCase {

    @Test
    fun test() {
        val breakfast = breakfast {
            eggs = 1
            bacon = true
            drink = Drink.TEA
            title = "Simple"
        }
        assertEquals(1, breakfast.eggs)
        assertEquals(true, breakfast.bacon)
        assertEquals(Drink.TEA, breakfast.drink)
        assertEquals("Simple", breakfast.title)
    }

    enum class Drink {
        WATER,
        COFFEE,
        TEA,
        NONE
    }

    abstract class Meal {
        data class Breakfast(
            val eggs: Int,
            val bacon: Boolean,
            val drink: Drink,
            val title: String
        ) : Meal()

        data class Diner(
            val title: String
        ) : Meal()
    }

    private class KotlinLikeBuilder {
        var eggs = 0
        var bacon = false
        var drink = Drink.NONE
        var title = ""

        fun build() = Meal.Breakfast(eggs, bacon, drink, title)
    }

    private fun breakfast(block: KotlinLikeBuilder.() -> Unit): Meal.Breakfast {
        val builder = KotlinLikeBuilder()
        builder.block()
        return builder.build()
    }
}