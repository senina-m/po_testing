package ru.sennik.lab1.story

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

/**
 * @author Natalia Nikonova
 */
class ProgramTest {
   @ParameterizedTest
   @EnumSource(KindOfProgram::class)
   fun `Program has words of its type`(type: KindOfProgram) {
      val name = "test program"
      val program = Program(name, type)

      val output = tapSystemOut {
         program.play()
      }

      val pattern = getProgramPlayPattern(name, type)
      Assertions.assertTrue(
         Regex(pattern)
            .containsMatchIn(output),
         "pattern: $pattern, output: $output"
      )
   }
}
