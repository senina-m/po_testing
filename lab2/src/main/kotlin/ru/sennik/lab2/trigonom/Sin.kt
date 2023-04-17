package ru.sennik.lab2.trigonom

import ru.sennik.lab2.Formula
import ru.sennik.lab2.exception.NotValidAccuracyException
import java.math.BigDecimal
import kotlin.math.PI

open class Sin : Formula {
    override fun count(x: Double, accuracy: Double): Double {
        if (accuracy >= 1 || accuracy <= 0) {
            throw NotValidAccuracyException(accuracy)
        }
        val realX = x % (PI * 2)
        var xm = BigDecimal(realX)
        val acc = BigDecimal(accuracy * 0.01)
        var sinX = BigDecimal(realX)
        var k = 1
        var factorial = BigDecimal.ONE
        var summand = BigDecimal.ONE
        while (summand >= acc || summand <= -acc) {
            factorial = factorial * BigDecimal(2*k+1) * BigDecimal(2*k)
            xm = xm * BigDecimal(realX) * BigDecimal(realX)
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