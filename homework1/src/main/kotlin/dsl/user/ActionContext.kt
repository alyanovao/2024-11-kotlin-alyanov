package ru.aao.dsl.user

@UserDsl
class ActionContext {

    private val _actions: MutableSet<Action> = mutableSetOf()

    fun build(): Set<Action> = _actions.toSet()
    fun add(action: Action) = _actions.add(action)

    fun add(action: String) = add(Action.valueOf(action))

    operator fun Action.unaryPlus() = add(this)

    operator fun String.unaryPlus() = add(this)

}