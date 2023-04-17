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
      @JvmStatic
      protected val sinFilename = "sin_to_dif.csv"
      @JvmStatic
      protected val cosFilename = "cos_to_dif.csv"
      @JvmStatic
      protected val cscFilename = "csc_to_dif.csv"
      @JvmStatic
      protected val tanFilename = "tan_to_dif.csv"
      @JvmStatic
      protected val cotFilename = "cot_to_dif.csv"

      @JvmStatic
      protected val lnFilename = "ln_to_dif.csv"
      @JvmStatic
      protected val log2Filename = "log2_to_dif.csv"
      @JvmStatic
      protected val log3Filename = "log3_to_dif.csv"
      @JvmStatic
      protected val log5Filename = "log5_to_dif.csv"
      @JvmStatic
      protected val log10Filename = "log10_to_dif.csv"

      protected const val resultFilename = "dif_fun_res.csv"

      @JvmStatic
      protected val defaultAccuracy = 0.01


      @JvmStatic
      fun funValuesMethodSource() = getArgsAndValues(resultFilename)
   }
}