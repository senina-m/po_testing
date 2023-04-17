package ru.sennik.lab2.trigonom

import ru.sennik.lab2.Formula
import ru.sennik.lab2.exception.FunctionNotExistsException
import kotlin.math.sin

/**
 * @author Natalia Nikonova
 */
open class Cot(
   private val sin: Sin,
   private val cos: Cos
) : Formula {
   override fun count(x: Double, accuracy: Double): Double {
      super.count(x, accuracy)
      val sinVal = sin.count(x, accuracy * 0.1)
      val delta = 0.0000000001
      println("x: $x, sinval $sinVal")
      if (sinVal >= -delta && sinVal <= delta) { throw FunctionNotExistsException(x, "cot") }
      return cos.count(x, accuracy * 0.1) / sinVal
   }
}
