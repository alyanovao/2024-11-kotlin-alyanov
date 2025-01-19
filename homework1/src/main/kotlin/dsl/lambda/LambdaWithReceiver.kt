package ru.aao.dsl.lambda

data class Engineer (
    var name: String = "",
    var age: Int = 0,
    var country: String = ""
)

fun main() {
    val john = Engineer()
    john.name = "Jon"
    john.age = 30
    john.country = "America"

    val bob = Engineer().apply {
        name = "Bob"
        age = 35
        country = "Dania"
    }

    println("${john}\n${bob}")
}