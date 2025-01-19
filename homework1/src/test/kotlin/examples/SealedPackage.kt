package examples

import additional.Student

fun main() {
    val s = Student("Artem", 10, 38)
        .also {
            println("My mane is ${it.name}")
        }
}