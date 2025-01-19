package additional

class User(private val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int by map
}

fun main() {
    val userData = mutableMapOf<String, Any?> (
        "name" to "Ivan",
        "age" to 30
    )

    val user = User(userData)
    println("${user.name} ${user.age}")

    user.age = 50
    println("${user.name} ${user.age}")
}