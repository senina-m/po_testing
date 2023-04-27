package ru.sennik.lab2.integrationTest

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import ru.sennik.lab2.DifficultFunction
import ru.sennik.lab2.DoubleRangeMatcher
import ru.sennik.lab2.DoubleRangeTwoPiRoundMatcher
import ru.sennik.lab2.exception.FunctionNotExistsException
import ru.sennik.lab2.getArgsAndValues
import ru.sennik.lab2.getMockLogFunction
import ru.sennik.lab2.getMockTrigFunction
import ru.sennik.lab2.log.Ln
import ru.sennik.lab2.log.Log10
import ru.sennik.lab2.log.Log2
import ru.sennik.lab2.log.Log3
import ru.sennik.lab2.log.Log5
import ru.sennik.lab2.trigonom.Cos
import ru.sennik.lab2.trigonom.Cot
import ru.sennik.lab2.trigonom.Csc
import ru.sennik.lab2.trigonom.Sin
import ru.sennik.lab2.trigonom.Tan
import kotlin.math.PI

/**
 * @author Natalia Nikonova
 */
class FullIntegrationTest : BaseIntegrationTest() {
   @ParameterizedTest
   @MethodSource("funcValuesMethodSource")
   fun checkFunctionExist(x: Double, result: Double) {
      val sin = Sin()
      val cos = Cos(sin)
      val cot = Cot(sin, cos)
      val tan = Tan(sin, cos)
      val csc = Csc(sin)
      val ln = Ln()
      val log10 = Log10(ln)
      val log5 = Log5(ln)
      val log3 = Log3(ln)
      val log2 = Log2(ln)
      val difFun = DifficultFunction(
         sin, cos, csc, tan, cot,
         ln, log2, log3, log5, log10
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
      val sin = Sin()
      val cos = Cos(sin)
      val cot = Cot(sin, cos)
      val tan = Tan(sin, cos)
      val csc = Csc(sin)
      val ln = Ln()
      val log10 = Log10(ln)
      val log5 = Log5(ln)
      val log3 = Log3(ln)
      val log2 = Log2(ln)
      val difFun = DifficultFunction(
         sin, cos, csc, tan, cot,
         ln, log2, log3, log5, log10
      )
      assertThrows<FunctionNotExistsException> { difFun.count(value, defaultAccuracy) }
   }

   companion object {
      @JvmStatic
      private val defaultCsc = getMockTrigFunction<Csc>(cscFilename)
      @JvmStatic
      private val defaultTan = getMockTrigFunction<Tan>(tanFilename)
      @JvmStatic
      private val defaultCot = getMockTrigFunction<Cot>(cotFilename)
      @JvmStatic
      private val defaultLn = getMockLogFunction<Ln>(lnFilename)
      @JvmStatic
      private val defaultLog2 = getMockLogFunction<Log2>(log2Filename)
      @JvmStatic
      private val defaultLog3 = getMockLogFunction<Log3>(log3Filename)
      @JvmStatic
      private val defaultLog5 = getMockLogFunction<Log5>(log5Filename)
      @JvmStatic
      private val defaultLog10 = getMockLogFunction<Log10>(log10Filename)

      @JvmStatic
      fun funcValuesMethodSource() = getArgsAndValues("zdif_ful_res.csv")

      @JvmStatic
      @BeforeAll
      fun setUpNotValidArg() {
         Mockito.`when`(
            defaultCsc.count(
               ArgumentMatchers.doubleThat(DoubleRangeTwoPiRoundMatcher(-2 * PI)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultCsc.count(
               ArgumentMatchers.doubleThat(DoubleRangeTwoPiRoundMatcher(-PI)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultCsc.count(
               ArgumentMatchers.doubleThat(DoubleRangeTwoPiRoundMatcher(0.0)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)

         Mockito.`when`(
            defaultTan.count(
               ArgumentMatchers.doubleThat(DoubleRangeTwoPiRoundMatcher(-3 * PI / 2)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultTan.count(
               ArgumentMatchers.doubleThat(DoubleRangeTwoPiRoundMatcher(-PI / 2)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)

         Mockito.`when`(
            defaultCot.count(
               ArgumentMatchers.doubleThat(DoubleRangeTwoPiRoundMatcher(-2 * PI)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultCot.count(
               ArgumentMatchers.doubleThat(DoubleRangeTwoPiRoundMatcher(-PI)),
               ArgumentMatchers.anyDouble()
            )
         )
            .thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultCot.count(
               ArgumentMatchers.doubleThat(DoubleRangeTwoPiRoundMatcher(0.0)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)

         Mockito.`when`(
            defaultLn.count(
               ArgumentMatchers.doubleThat(DoubleRangeMatcher(0.0)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultLog2.count(
               ArgumentMatchers.doubleThat(DoubleRangeMatcher(0.0)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultLog3.count(
               ArgumentMatchers.doubleThat(DoubleRangeMatcher(0.0)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultLog5.count(
               ArgumentMatchers.doubleThat(DoubleRangeMatcher(0.0)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultLog10.count(
               ArgumentMatchers.doubleThat(DoubleRangeMatcher(0.0)),
               ArgumentMatchers.anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
      }
   }
}