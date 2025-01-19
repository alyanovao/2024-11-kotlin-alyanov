import org.junit.jupiter.api.Assertions.assertTrue
import ru.aao.dsl.user.*
import kotlin.test.Test
import kotlin.test.assertEquals

class UserTestCase {

    @Test
    fun test() {
        val user = buildUser {
            name {
                first = "Ivan"
                last = "Vasilievich"
            }
            contacts {
                email = "ivan-vasilievich@gmail.com"
                phone = "79181234567"
            }
            action {
                add(Action.UPDATE)
                add(Action.ADD)

                +Action.DELETE
                +Action.READ
            }
            availability {
                mon("11:30")
                fri("18:00")
                tomorrow("10:00")
            }
        }

        assertTrue(user.id.isNotEmpty())
        assertEquals("Ivan", user.firstName)
        assertEquals("", user.secondName)
        assertEquals("Vasilievich", user.lastName)
        assertEquals("ivan-vasilievich@gmail.com", user.email)
        assertEquals("79181234567", user.phone)
        assertEquals(setOf(Action.UPDATE, Action.ADD, Action.READ, Action.DELETE), user.actions)
        assertEquals(3, user.available.size)
    }
}