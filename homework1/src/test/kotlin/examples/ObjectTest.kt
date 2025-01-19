package examples

import kotlin.test.Test

class ObjectExample {
    companion object {
        init {
            println("init companion")
        }

        fun doSome() {
            println("companion object")
        }
    }

    object A {
        init {
            println("A init")
        }

        fun doSome() {
            println("object A")
        }
    }
}

object B {
    init {
        println("B init")
    }

    fun doSome() {
        println("object B")
    }
}

class ObjectTest {

    @Test
    fun test() {
        ObjectExample.doSome()
        ObjectExample.A.doSome()
        B.doSome()
        println()
    }
}