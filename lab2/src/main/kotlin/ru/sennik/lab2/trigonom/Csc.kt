package ru.sennik.lab2.trigonom

import ru.sennik.lab2.Formula
import ru.sennik.lab2.exception.FunctionNotExistsException

/**
 * @author Natalia Nikonova
 */
open class Csc(
   private val sin: Sin
) : Formula {
   override fun count(x: Double, accuracy: Double): Double {
      super.count(x, accuracy)
      val sinVal = sin.count(x, accuracy * 0.1)
      val delta = 0.0000000001
      if (sinVal >= -delta && sinVal <= delta) { throw FunctionNotExistsException(x, "csc") }
      return 1 / sinVal
   }
}
