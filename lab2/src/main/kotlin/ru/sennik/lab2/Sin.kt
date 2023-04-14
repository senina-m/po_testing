package ru.sennik.lab2

import java.math.BigDecimal

class Sin (private var x: Double, private var  accuracy: Double) : Formula {
    override fun count():Double {
        if (accuracy >= 1 || accuracy <= 0) {
            throw RuntimeException("Accuracy has to be from 0 to 1!")
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