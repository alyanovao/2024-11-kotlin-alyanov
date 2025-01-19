package additional

sealed class SealedPerson(open val age: Int)

data class Student(val name: String, val grade: Int, override val age: Int): SealedPerson(age)
data class Teacher(val name: String, val subject: String, override val age: Int): SealedPerson(age)

fun introduce(person: SealedPerson) {
    when (person) {
        is Student -> println("Student: ${person.name}, age: ${person.age} is grade ${person.grade}")
        is Teacher -> println("Teacher ${person.name}, age: ${person.age} teaches ${person.subject}")
    }
}

fun main() {
    val student = Student("Alice", 10, 20)
    val teacher = Teacher("Bob", "Mathematics", 45)

    introduce(student)
    introduce(teacher)
}