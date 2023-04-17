package ru.sennik.lab2.integrationTest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import ru.sennik.lab2.DifficultFunction
import ru.sennik.lab2.exception.FunctionNotExistsException
import ru.sennik.lab2.trigonom.Cos
import ru.sennik.lab2.trigonom.Cot
import ru.sennik.lab2.trigonom.Csc
import ru.sennik.lab2.trigonom.Tan
import kotlin.math.PI

/**
 * @author Natalia Nikonova
 */
class IntegrationCosTest : BaseIntegrationTest() {
   @ParameterizedTest
   @MethodSource("funValuesMethodSource")
   fun checkFunctionExist(x: Double, result: Double) {
      val cos = Cos(defaultSin)
      val cot = Cot(defaultSin, cos)
      val tan = Tan(defaultSin, cos)
      val csc = Csc(defaultSin)
      val difFun = DifficultFunction(
         defaultSin, cos, csc, tan, cot,
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
      val cos = Cos(defaultSin)
      val cot = Cot(defaultSin, cos)
      val tan = Tan(defaultSin, cos)
      val csc = Csc(defaultSin)
      val difFun = DifficultFunction(
         defaultSin, cos, csc, tan, cot,
         defaultLn, defaultLog2, defaultLog3, defaultLog5, defaultLog10
      )
      assertThrows<FunctionNotExistsException> { difFun.count(value, defaultAccuracy) }
   }
}
