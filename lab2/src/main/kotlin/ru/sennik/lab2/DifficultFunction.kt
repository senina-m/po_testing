package ru.sennik.lab2

import ru.sennik.lab2.exception.FunctionNotExistsException
import ru.sennik.lab2.log.*
import ru.sennik.lab2.trigonom.*
import kotlin.math.pow

/**
 * @author Natalia Nikonova
 */
class DifficultFunction(
   private val sin: Sin,
   private val cos: Cos,
   private val csc: Csc,
   private val tan: Tan,
   private val cot: Cot,
   private val ln: Ln,
   private val log2: Log2,
   private val log3: Log3,
   private val log5: Log5,
   private val log10: Log10
) : Formula {
   override fun count(x: Double, accuracy: Double): Double {
      val result: Double
      if (x <= 0) {
         val cosVal = cos.count(x, accuracy)
         if (cosVal == 0.0) {
            throw FunctionNotExistsException(x, "difficult fun")
         }
         try {
            val cscVal = csc.count(x, accuracy)
            val numerator = (cot.count(x, accuracy) - cscVal) * tan.count(x, accuracy) * cscVal - sin.count(x, accuracy)
            result = numerator / (2 * cosVal)
         } catch (ex: FunctionNotExistsException) {
            throw FunctionNotExistsException(x, "difficult fun", ex)
         }
      } else {
         try {
            val log10Val = log10.count(x, accuracy)
            val log5Val = log5.count(x, accuracy)
            val reduced = ((log10Val * log3.count(x, accuracy) - log5Val) * log2.count(x, accuracy).pow(3)).pow(2)
            val subtrahend = ln.count(x, accuracy) * (log5Val.pow(2) + log10Val.pow(3))
            result = reduced - subtrahend
         } catch (ex: FunctionNotExistsException) {
            throw FunctionNotExistsException(x, "difficult fun", ex)
         }
      }
      return result
   }
}
