package ru.sennik.lab2.trigonom

import ru.sennik.lab2.Formula
import ru.sennik.lab2.exception.FunctionNotExistsException

/**
 * @author Natalia Nikonova
 */
open class Tan(
   private val sin: Sin,
   private val cos: Cos
) : Formula {
   override fun count(x: Double, accuracy: Double): Double {
      super.count(x, accuracy)
      val cosVal = cos.count(x, accuracy * 0.1)
      val delta = 0.0000000001
      if (cosVal >= -delta && cosVal <= delta) { throw FunctionNotExistsException(x, "tan") }
      return sin.count(x, accuracy * 0.1) / cosVal
   }
}
