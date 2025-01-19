package additional

object GlobalGenerator: Iterator<Long> {
    private var nextId: Long = 0L
    override fun hasNext(): Boolean = true
    override fun next(): Long = nextId++
}

fun someFun(iterator: Iterator<Long>) {
    println("Next ID = ${iterator.next()}")
}

fun main() {
    for (i in 1 .. 5) {
        println("${GlobalGenerator.next()}")
    }

    someFun(GlobalGenerator)
    someFun(GlobalGenerator)
    someFun(GlobalGenerator)
    someFun(object: Iterator<Long> {
        private var nextId: Long = 1000L
        override fun hasNext(): Boolean = true
        override fun next(): Long = nextId++
    })
}