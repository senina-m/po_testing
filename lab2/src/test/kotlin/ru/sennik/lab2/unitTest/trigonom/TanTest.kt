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
import ru.sennik.lab2.trigonom.Cos
import ru.sennik.lab2.trigonom.Sin
import ru.sennik.lab2.trigonom.Tan
import kotlin.math.PI

/**
 * @author Natalia Nikonova
 */
class TanTest {
   private val defaultAccuracy = 0.01
   private val defaultSin = getMockTrigFunction<Sin>(sinFilename)
   private val defaultCos = getMockTrigFunction<Cos>(cosFilename)

   @ParameterizedTest
   @MethodSource("tanValuesMethodSource")
   fun checkFunctionExist(value: Double) {
      val tan = Tan(defaultSin, defaultCos)
      Assertions.assertEquals(
         kotlin.math.tan(value),
         tan.count(value, defaultAccuracy),
         defaultAccuracy
      )
   }

   @ParameterizedTest
   @ValueSource(doubles = [-PI / 2, PI / 2])
   fun checkFunctionNotExist(value: Double) {
      val tan = Tan(defaultSin, defaultCos)
      assertThrows<FunctionNotExistsException> { tan.count(value, defaultAccuracy) }
   }

   @ParameterizedTest
   @ValueSource(doubles = [0.00001, 0.99999])
   fun checkAllowedAccuracy(accuracy: Double) {
      val tan = Tan(defaultSin, defaultCos)
      assertDoesNotThrow { tan.count(1.0, accuracy) }
   }

   @ParameterizedTest
   @ValueSource(doubles = [0.0, 1.0])
   fun checkNotAllowedAccuracy(accuracy: Double) {
      val tan = Tan(defaultSin, defaultCos)
      assertThrows<NotValidAccuracyException> { tan.count(1.0, accuracy) }
   }

   companion object {
      private const val sinFilename = "sin_to_tan.csv"
      private const val cosFilename = "cos_to_tan.csv"

      @JvmStatic
      fun tanValuesMethodSource() = getValues(sinFilename)
   }
}