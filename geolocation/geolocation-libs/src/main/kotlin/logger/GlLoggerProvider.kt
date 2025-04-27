package logger

import kotlin.reflect.KClass
import kotlin.reflect.KFunction

open class GlLoggerProvider(
    private val provider: (String) -> IGlLogWrapper = { IGlLogWrapper.DEFAULT }
) {
    fun logger(loggerId: String): IGlLogWrapper = provider(loggerId)
    fun logger(clazz: KClass<*>): IGlLogWrapper = provider(clazz.qualifiedName ?: clazz.simpleName ?: "class")
    fun logger(function: KFunction<*>): IGlLogWrapper = provider(function.name)
}