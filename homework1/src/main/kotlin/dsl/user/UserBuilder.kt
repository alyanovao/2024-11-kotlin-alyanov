package ru.aao.dsl.user

import java.time.LocalDateTime
import java.util.*

@UserDsl
class UserBuilder {
    private var id: String = UUID.randomUUID().toString()

    private var firstName: String = ""
    private var secondName: String = ""
    private var lastName: String = ""

    private var phone: String = ""
    private var email: String = ""

    private var actions: Set<Action> = emptySet()

    private var available: List<LocalDateTime> = emptyList()

    fun name(block: NameContext.() -> Unit) {
        val context = NameContext().apply(block)
        firstName = context.first
        secondName = context.second
        lastName = context.last
    }

    fun contacts(block: ContactContext.() -> Unit) {
        val context = ContactContext().apply(block)
        phone = context.phone
        email = context.email
    }

    fun action(block: ActionContext.() -> Unit) {
        val context = ActionContext().apply(block)
        actions = context.build()
    }

    fun availability(block: AvailableContext.() -> Unit) {
        val context = AvailableContext().apply(block)
        available = context.availabilities
    }

    fun build() = User(
        id = id,
        firstName = firstName,
        secondName = secondName,
        lastName = lastName,
        phone = phone,
        email = email,
        actions = actions,
        available = available
    )
}