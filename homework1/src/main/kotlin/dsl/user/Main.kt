package ru.aao.dsl.user

fun main() {
    val user = buildUser {
        name {
            first = "Bob"
            second = "M"
            last = "Smith"
        }

        contacts {
            phone = "12345"
            email = "bob@mail.ru"
        }

        action {
            +Action.CREATE
            +"UPDATE"
        }

        availability {
            mon("10:00")
            tomorrow("14:00")
        }
    }
    println(user)
}