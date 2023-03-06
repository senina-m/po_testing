package ru.sennik.lab1.story

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author Natalia Nikonova
 */
class SpaceShipTest {
    private lateinit var ship: SpaceShip

    @BeforeEach
    fun setUp() {
        ship = SpaceShip("test ship", "Earth")
    }

    @Test
    fun `Add person to passengers successful`() {
        val zafod = Human("Зафод")

        ship.addPerson(zafod)

        Assertions.assertEquals(setOf(zafod), ship.getShipPassengers())
    }

    @Test
    fun `Add already exists on ship person to passengers failed`() {
        val zafod = Human("Зафод")

        ship.addPerson(zafod)

        Assertions.assertThrows(
            RuntimeException::class.java,
            { ship.addPerson(zafod) },
            "$zafod is already on a board of ${ship.name}"
        )
    }

    @Test
    fun `Delete exists in ship person from passengers successful` () {
        val zafod = Human("Зафод")

        ship.addPerson(zafod)
        ship.removePerson(zafod)

        Assertions.assertEquals(emptySet<Human>(), ship.getShipPassengers())
    }

    @Test
    fun `Delete not exists in ship person from passengers failed`() {
        val zafod = Human("Зафод")

        Assertions.assertThrows(
            RuntimeException::class.java,
            { ship.removePerson(zafod) },
            "No $zafod on a board of ${ship.name}"
        )
    }

    @Test
    fun `Person on ship start listen radio and move - program changed`() {
        val firstProgram = ship.getCurrentRadioProgram()
        val zafod = Human("Зафод")
        ship.addPerson(zafod)
        ship.startListenRadio(zafod)

        zafod.move()

        Assertions.assertNotEquals(firstProgram, ship.getCurrentRadioProgram())
    }

    @Test
    fun `Person not on ship move - program not changed`() {
        val firstProgram = ship.getCurrentRadioProgram()
        val zafod = Human("Зафод")

        zafod.move()

        Assertions.assertEquals(firstProgram, ship.getCurrentRadioProgram())
    }

    @Test
    fun `Person on ship and not listen radio move - program not changed`() {
        val firstProgram = ship.getCurrentRadioProgram()
        val zafod = Human("Зафод")
        ship.addPerson(zafod)

        zafod.move()

        Assertions.assertEquals(firstProgram, ship.getCurrentRadioProgram())
    }

    @Test
    fun `Person on ship start and stop listen radio and move - program not changed`() {
        val firstProgram = ship.getCurrentRadioProgram()
        val zafod = Human("Зафод")
        ship.addPerson(zafod)
        ship.startListenRadio(zafod)

        ship.stopListenRadio(zafod)
        zafod.move()

        Assertions.assertEquals(firstProgram, ship.getCurrentRadioProgram())
    }

    @Test
    fun `Person on ship start listened radio delete from passengers and move - program not change`() {
        val firstProgram = ship.getCurrentRadioProgram()
        val zafod = Human("Зафод")
        ship.addPerson(zafod)
        ship.startListenRadio(zafod)

        ship.removePerson(zafod)
        zafod.move()

        Assertions.assertEquals(firstProgram, ship.getCurrentRadioProgram())
    }

    @Test
    fun `Person on ship stop listen radio without start is failed`() {
        val zafod = Human("Зафод")
        ship.addPerson(zafod)

        Assertions.assertThrows(
            RuntimeException::class.java,
            { ship.stopListenRadio(zafod) },
            "$zafod not listen radio"
        )
    }

    @Test
    fun `Person not on ship start listen radio is failed`() {
        val zafod = Human("Зафод")

        Assertions.assertThrows(
            RuntimeException::class.java,
            { ship.startListenRadio(zafod) },
            "No $zafod on a board of ${ship.name}"
        )
    }

    @Test
    fun `Person not on ship stop listen radio is failed`() {
        val zafod = Human("Зафод")

        Assertions.assertThrows(
            RuntimeException::class.java,
            { ship.stopListenRadio(zafod) },
            "No $zafod on a board of ${ship.name}"
        )
    }
}
