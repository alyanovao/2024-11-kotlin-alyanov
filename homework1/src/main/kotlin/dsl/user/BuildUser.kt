package ru.aao.dsl.user

fun buildUser(block: UserBuilder.() -> Unit): User = UserBuilder().apply(block).build()