package ru.sennik.lab2.trigonom

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import ru.sennik.lab2.exception.FunctionNotExistsException
import ru.sennik.lab2.getMockFunction
import ru.sennik.lab2.getValues
import kotlin.math.PI

/**
 * @author Natalia Nikonova
 */
class CscTest {
   private val defaultAccuracy = 0.01
   private val defaultSin = getMockFunction<Sin>(filename)

   @ParameterizedTest
   @MethodSource("cscValuesMethodSource")
   fun checkFunctionExist(value: Double) {
      val csc = Csc(defaultSin)
      Assertions.assertEquals(
         1 / kotlin.math.sin(value),
         csc.count(value, defaultAccuracy),
         defaultAccuracy
      )
   }

   @ParameterizedTest
   @ValueSource(doubles = [0.0, PI, 2 * PI])
   fun checkFunctionNotExist(value: Double) {
      val csc = Csc(defaultSin)
      assertThrows<FunctionNotExistsException> { csc.count(value, defaultAccuracy) }
   }

   companion object {
      private const val filename = "sin_to_csc.csv"

      @JvmStatic
      fun cscValuesMethodSource() = getValues(filename)
   }
}
