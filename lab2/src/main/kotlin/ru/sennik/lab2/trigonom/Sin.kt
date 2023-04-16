package ru.sennik.lab2.trigonom

import ru.sennik.lab2.Formula
import ru.sennik.lab2.exception.NotValidAccuracyException
import java.math.BigDecimal

class Sin : Formula {
    override fun count(x: Double, accuracy: Double): Double {
        if (accuracy >= 1 || accuracy <= 0) {
            throw NotValidAccuracyException(accuracy)
        }
        var xm = BigDecimal(x)
        val acc = BigDecimal(accuracy * 0.1)
        var sinX = BigDecimal(x)
        var k = 1
        var factorial = BigDecimal.ONE
        var summand = BigDecimal.ONE
        while (summand >= acc) {
            factorial = factorial * BigDecimal(2*k+1) * BigDecimal(2*k)
            xm = xm * BigDecimal(x) * BigDecimal(x)
            summand = xm/factorial

            if (k % 2 == 0) {
                sinX += summand
            } else {
                sinX -= summand
            }
            k++
        }
        return sinX.toDouble()
    }
}