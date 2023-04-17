package ru.sennik.lab2.integrationTest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import ru.sennik.lab2.DifficultFunction
import ru.sennik.lab2.exception.FunctionNotExistsException
import ru.sennik.lab2.trigonom.Cot
import kotlin.math.PI

/**
 * @author Natalia Nikonova
 */
class IntegrationCotTest : BaseIntegrationTest() {
   @ParameterizedTest
   @MethodSource("funValuesMethodSource")
   fun checkFunctionExist(x: Double, result: Double) {
      val cot = Cot(defaultSin, defaultCos)
      val difFun = DifficultFunction(
         defaultSin, defaultCos, defaultCsc, defaultTan, cot,
         defaultLn, defaultLog2, defaultLog3, defaultLog5, defaultLog10
      )
      Assertions.assertEquals(
         result,
         difFun.count(x, defaultAccuracy),
         defaultAccuracy
      )
   }

   @ParameterizedTest
   @ValueSource(doubles = [-PI * 2, -3 * PI / 2, -PI, -PI / 2, 0.0])
   fun checkFunctionNotExist(value: Double) {
      val cot = Cot(defaultSin, defaultCos)
      val difFun = DifficultFunction(
         defaultSin, defaultCos, defaultCsc, defaultTan, cot,
         defaultLn, defaultLog2, defaultLog3, defaultLog5, defaultLog10
      )
      assertThrows<FunctionNotExistsException> { difFun.count(value, defaultAccuracy) }
   }
}
