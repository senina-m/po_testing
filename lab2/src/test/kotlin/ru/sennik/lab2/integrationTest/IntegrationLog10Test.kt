package ru.sennik.lab2.integrationTest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import ru.sennik.lab2.DifficultFunction
import ru.sennik.lab2.exception.FunctionNotExistsException
import ru.sennik.lab2.log.Log10
import kotlin.math.PI

/**
 * @author Natalia Nikonova
 */
class IntegrationLog10Test : BaseIntegrationTest() {
   @ParameterizedTest
   @MethodSource("funValuesMethodSource")
   fun checkFunctionExist(x: Double, result: Double) {
      val log10 = Log10(defaultLn)
      val difFun = DifficultFunction(
         defaultSin, defaultCos, defaultCsc, defaultTan, defaultCot,
         defaultLn, defaultLog2, defaultLog3, defaultLog5, log10
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
      val log10 = Log10(defaultLn)
      val difFun = DifficultFunction(
         defaultSin, defaultCos, defaultCsc, defaultTan, defaultCot,
         defaultLn, defaultLog2, defaultLog3, defaultLog5, log10
      )
      assertThrows<FunctionNotExistsException> { difFun.count(value, defaultAccuracy) }
   }
}
