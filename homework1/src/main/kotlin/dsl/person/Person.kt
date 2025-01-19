package ru.aao.dsl.person

@DslMarker
annotation class PersonDsl

@PersonDsl
data class Person(
    var id: Long = 0L,
    var name: String = "",
    var age: Int = 0,

    var contacts: Contacts = Contacts()
)

@PersonDsl
data class Contacts(
    var phone: String = "",
    var email: String = ""
)

@PersonDsl
fun person(block: Person.() -> Unit): Person {
    return Person().apply(block)
}

@PersonDsl
fun Person.contacts(block: Contacts.() -> Unit): Contacts {
    return contacts.apply(block)
}

fun main() {
    val person = person {
        id = 1
        name = "Nikolos"
        age = 35
        contacts {
            phone = "7918-123-45-67"
            email = "nikolos@mail.ru"
        }
    }
    println(person)
}