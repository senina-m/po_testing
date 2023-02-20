package ru.sennik.lab1.story

import com.github.stefanbirkner.systemlambda.SystemLambda
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author Natalia Nikonova
 */
class RadioTest {
    private lateinit var radio: Radio

    @BeforeEach
    fun setUp() {
        radio = Radio()
    }

    @Test
    fun `Then move happened, then program changed and next play`() {
       val firstProgram = radio.getCurrentProgram()

       val output = SystemLambda.tapSystemOut {
           radio.moveHappened()
       }

       val secondProgram = radio.getCurrentProgram()
       Assertions.assertNotEquals(firstProgram, radio.getCurrentProgram())
       Assertions.assertTrue(
           Regex(getProgramPlayPattern(secondProgram.name, secondProgram.type))
               .containsMatchIn(output)
       )
    }

    @Test
   fun `Then move happened and current final program, then changed to start program`() {
       val firstProgram = radio.getCurrentProgram()

       do {
           radio.moveHappened()
       } while (firstProgram != radio.getCurrentProgram())

       Assertions.assertEquals(firstProgram, radio.getCurrentProgram())
   }
}
