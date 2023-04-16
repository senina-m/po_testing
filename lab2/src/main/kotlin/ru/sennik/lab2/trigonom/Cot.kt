package ru.sennik.lab2.trigonom

import ru.sennik.lab2.Formula
import ru.sennik.lab2.exception.FunctionNotExistsException

/**
 * @author Natalia Nikonova
 */
class Cot(
   private val sin: Sin,
   private val cos: Cos
) : Formula {
   override fun count(x: Double, accuracy: Double): Double {
      val sinVal = sin.count(x, accuracy)
      if (sinVal == 0.0) { throw FunctionNotExistsException(x, "tan") }
      return cos.count(x, accuracy) / sinVal
   }
}
