package ru.sennik.lab2.integrationTest

import ru.sennik.lab2.getArgsAndValues

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