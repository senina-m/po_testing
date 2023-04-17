package ru.sennik.lab2.unitTest.trigonom

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import ru.sennik.lab2.exception.FunctionNotExistsException
import ru.sennik.lab2.exception.NotValidAccuracyException
import ru.sennik.lab2.getMockTrigFunction
import ru.sennik.lab2.getValues
import ru.sennik.lab2.trigonom.Csc
import ru.sennik.lab2.trigonom.Sin
import kotlin.math.PI

/**
 * @author Natalia Nikonova
 */
class CscTest {
   private val defaultAccuracy = 0.01
   private val defaultSin = getMockTrigFunction<Sin>(filename)

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

   @ParameterizedTest
   @ValueSource(doubles = [0.00001, 0.99999])
   fun checkAllowedAccuracy(accuracy: Double) {
      val csc = Csc(defaultSin)
      assertDoesNotThrow { csc.count(PI / 2, accuracy) }
   }

   @ParameterizedTest
   @ValueSource(doubles = [0.0, 1.0])
   fun checkNotAllowedAccuracy(accuracy: Double) {
      val csc = Csc(defaultSin)
      assertThrows<NotValidAccuracyException> { csc.count(PI / 2, accuracy) }
   }

   companion object {
      private const val filename = "sin_to_csc.csv"

      @JvmStatic
      fun cscValuesMethodSource() = getValues(filename)
   }
}
