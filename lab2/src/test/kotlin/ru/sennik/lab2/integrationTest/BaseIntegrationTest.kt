package ru.sennik.lab2.integrationTest

import org.junit.jupiter.api.BeforeAll
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.doubleThat
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito
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
import kotlin.math.ln

/**
 * @author Natalia Nikonova
 */
open class BaseIntegrationTest {
   companion object {
      protected const val sinFilename = "sin_to_dif.csv"
      protected const val cosFilename = "cos_to_dif.csv"
      protected const val cscFilename = "csc_to_dif.csv"
      protected const val tanFilename = "tan_to_dif.csv"
      protected const val cotFilename = "cot_to_dif.csv"

      protected const val lnFilename = "ln_to_dif.csv"
      protected const val log2Filename = "log2_to_dif.csv"
      protected const val log3Filename = "log3_to_dif.csv"
      protected const val log5Filename = "log5_to_dif.csv"
      protected const val log10Filename = "log10_to_dif.csv"

      protected const val resultFilename = "dif_fun_res.csv"

      @JvmStatic
      protected val defaultAccuracy = 0.01
      @JvmStatic
      protected val defaultSin = getMockTrigFunction<Sin>(sinFilename)
      @JvmStatic
      protected val defaultCos = getMockTrigFunction<Cos>(cosFilename)
      @JvmStatic
      protected val defaultCsc = getMockTrigFunction<Csc>(cscFilename)
      @JvmStatic
      protected val defaultTan = getMockTrigFunction<Tan>(tanFilename)
      @JvmStatic
      protected val defaultCot = getMockTrigFunction<Cot>(cotFilename)
      @JvmStatic
      protected val defaultLn = getMockLogFunction<Ln>(lnFilename)
      @JvmStatic
      protected val defaultLog2 = getMockLogFunction<Log2>(log2Filename)
      @JvmStatic
      protected val defaultLog3 = getMockLogFunction<Log3>(log3Filename)
      @JvmStatic
      protected val defaultLog5 = getMockLogFunction<Log5>(log5Filename)
      @JvmStatic
      protected val defaultLog10 = getMockLogFunction<Log10>(log10Filename)

      @JvmStatic
      fun funValuesMethodSource() = getArgsAndValues(resultFilename)

      @JvmStatic
      @BeforeAll
      fun setUpNotValidArg() {
         Mockito.`when`(
            defaultCsc.count(
               doubleThat(DoubleRangeTwoPiRoundMatcher(-2 * PI)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultCsc.count(
               doubleThat(DoubleRangeTwoPiRoundMatcher(-PI)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultCsc.count(
               doubleThat(DoubleRangeTwoPiRoundMatcher(0.0)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)

         Mockito.`when`(
            defaultTan.count(
               doubleThat(DoubleRangeTwoPiRoundMatcher(-3 * PI / 2)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultTan.count(
               doubleThat(DoubleRangeTwoPiRoundMatcher(-PI / 2)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)

         Mockito.`when`(
            defaultCot.count(
               doubleThat(DoubleRangeTwoPiRoundMatcher(-2 * PI)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultCot.count(
               doubleThat(DoubleRangeTwoPiRoundMatcher(-PI)),
               anyDouble()
            )
         )
            .thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultCot.count(
               doubleThat(DoubleRangeTwoPiRoundMatcher(0.0)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)

         Mockito.`when`(
            defaultLn.count(
               doubleThat(DoubleRangeMatcher(0.0)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultLog2.count(
               doubleThat(DoubleRangeMatcher(0.0)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultLog3.count(
               doubleThat(DoubleRangeMatcher(0.0)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultLog5.count(
               doubleThat(DoubleRangeMatcher(0.0)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)
         Mockito.`when`(
            defaultLog10.count(
               doubleThat(DoubleRangeMatcher(0.0)),
               anyDouble()
            )
         ).thenThrow(FunctionNotExistsException::class.java)

         /*Mockito.`when`(defaultLn.count(eq(2.0), anyDouble())).thenReturn(ln(2.0))
         Mockito.`when`(defaultLn.count(eq(3.0), anyDouble())).thenReturn(ln(3.0))
         Mockito.`when`(defaultLn.count(eq(5.0), anyDouble())).thenReturn(ln(5.0))
         Mockito.`when`(defaultLn.count(eq(10.0), anyDouble())).thenReturn(ln(10.0))*/
      }
   }
}