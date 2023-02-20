package ru.sennik.lab1.story

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author Natalia Nikonova
 */
class HumanTest {
    private lateinit var person: Human
    private lateinit var notifyObject: ObjectToNotifyMove

    @BeforeEach
    fun setUp() {
        person = Human("Testik")
        notifyObject = SimpleObjectToNotifyMoved()
    }

    @Test
    fun `Add notify object successful`() {
        person.addObjectToNotifyMove(notifyObject)

        Assertions.assertEquals(setOf(notifyObject), person.getObjectsToNotify())
    }

    @Test
    fun `Add already notified object failed`() {
        person.addObjectToNotifyMove(notifyObject)

        Assertions.assertThrows(
            RuntimeException::class.java,
            { person.addObjectToNotifyMove(notifyObject) },
            "$person already notifies $notifyObject"
        )
    }

    @Test
    fun `Remove notified object successful`() {
        person.addObjectToNotifyMove(notifyObject)

        person.unsubscribeFromNotifyMove(notifyObject)

        Assertions.assertEquals(emptySet<ObjectToNotifyMove>(), person.getObjectsToNotify())
    }

    @Test
    fun `Remove not notified object failed`() {
        Assertions.assertThrows(
            RuntimeException::class.java,
            { person.unsubscribeFromNotifyMove(notifyObject) },
            "$person not notifies $notifyObject"
        )
    }

    @Test
    fun `Notified object received than person move`() {
        person.addObjectToNotifyMove(notifyObject)

        val output = tapSystemOut {
            person.move()
        }

        Assertions.assertEquals("Person moved\r\n", output)
    }

    @Test
    fun `Not notified object don't received than person move`() {
        val output = tapSystemOut {
            person.move()
        }

        Assertions.assertEquals("", output)
    }

    private class SimpleObjectToNotifyMoved : ObjectToNotifyMove {
        override fun moveHappened() {
            println("Person moved")
        }
    }
}
