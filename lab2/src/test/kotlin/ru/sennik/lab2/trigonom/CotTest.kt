package ru.sennik.lab2.trigonom

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
import kotlin.math.PI

/**
 * @author Natalia Nikonova
 */
class CotTest {
   private val defaultAccuracy = 0.01
   private val defaultSin = getMockTrigFunction<Sin>(sinFilename)
   private val defaultCos = getMockTrigFunction<Cos>(cosFilename)

   @ParameterizedTest
   @MethodSource("cotValuesMethodSource")
   fun checkFunctionExist(value: Double) {
      val cot = Cot(defaultSin, defaultCos)
      Assertions.assertEquals(
         1 / kotlin.math.tan(value),
         cot.count(value, defaultAccuracy),
         defaultAccuracy
      )
   }

   @ParameterizedTest
   @ValueSource(doubles = [0.0, PI])
   fun checkFunctionNotExist(value: Double) {
      val cot = Cot(defaultSin, defaultCos)
      assertThrows<FunctionNotExistsException> { cot.count(value, defaultAccuracy) }
   }

   @ParameterizedTest
   @ValueSource(doubles = [0.00001, 0.99999])
   fun checkAllowedAccuracy(accuracy: Double) {
      val cot = Cot(defaultSin, defaultCos)
      assertDoesNotThrow { cot.count(1.0, accuracy) }
   }

   @ParameterizedTest
   @ValueSource(doubles = [0.0, 1.0])
   fun checkNotAllowedAccuracy(accuracy: Double) {
      val cot = Cot(defaultSin, defaultCos)
      assertThrows<NotValidAccuracyException> { cot.count(1.0, accuracy) }
   }

   companion object {
      private const val sinFilename = "sin_to_cot.csv"
      private const val cosFilename = "cos_to_cot.csv"

      @JvmStatic
      fun cotValuesMethodSource() = getValues(sinFilename)
   }
}
