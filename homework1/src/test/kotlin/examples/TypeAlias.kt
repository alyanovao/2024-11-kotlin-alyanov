package examples

import kotlin.test.Test

typealias StringAlias = String

@JvmInline
value class StringInline(val s: String)

class TypeAlias {
    val nameAlias: StringAlias = "name"
    val name: String = nameAlias
    val nameInline: StringInline = StringInline("name")
    val nameField: String = nameInline.s

    @Test
    fun test() {
        println(nameAlias)
        println(name)
        println(nameInline)
        println(name)
        println(nameField)
    }

}