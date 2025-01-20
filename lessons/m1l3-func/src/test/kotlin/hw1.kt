import junit.framework.TestCase.assertEquals
import kotlin.test.Test

/*
* Реализовать функцию, которая преобразует список словарей строк в ФИО
* Функцию сделать с использованием разных функций для разного числа составляющих имени
* Итого, должно получиться 4 функции
*
* Для успешного решения задания, требуется раскомментировать тест, тест должен выполняться успешно
* */
class HomeWork1Test {

    @Test
    fun mapListToNamesTest() {
        val input = listOf(
            mapOf(
                "first" to "Иван",
                "middle" to "Васильевич",
                "last" to "Рюрикович",
            ),
            mapOf(
                "first" to "Петька",
            ),
            mapOf(
                "first" to "Сергей",
                "last" to "Королев",
            ),
        )
        val expected = listOf(
            "Рюрикович Иван Васильевич",
            "Петька",
            "Королев Сергей",
        )
        val res = mapListToNames(input)
        assertEquals(expected, res)
    }

}

private fun mapListToNames(input: List<Map<String, String>>): List<String> {
    return input.map {
        when(it.size) {
            1 -> map2StringWithWord(it)
            2 -> map2StringWith2Words(it)
            3 -> map2StringWith3Words(it)
            else -> throw RuntimeException("Неверное количество элементов")
        }
    }
}

private fun map2StringWithWord(params: Map<String, String>): String {
    return "${params["first"]}"
}
private fun map2StringWith2Words(params: Map<String, String>): String {
    return "${params["last"]} ${params["first"]}"
}

private fun map2StringWith3Words(params: Map<String, String>): String {
    return "${params["last"]} ${params["first"]} ${params["middle"]}"
}
