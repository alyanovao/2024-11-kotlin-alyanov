package ru.aao.dsl.lambda

import java.util.Locale

fun String.modify(modification: String.() -> String): String {
    return modification()
}

fun main() {
    val result = "Hello".modify {
        this.uppercase(Locale.getDefault())
    }
    println(result)
}