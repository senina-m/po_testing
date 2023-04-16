package ru.sennik.lab2.log

import ru.sennik.lab2.Formula
import java.math.BigDecimal

class Ln() : Formula {
    override fun count(x: Double, accuracy: Double): Double {
        if (accuracy >= 1 || accuracy <= 0) {
            throw RuntimeException("Accuracy has to be from 0 to 1!")
        }

        if (x <= 0) {
            throw RuntimeException("x has to be from 0, but it is $x!")
        }else {
            return if (x <= 0.59){
                nearZero(x, accuracy)
            }else{
                notNearZero(x, accuracy)
            }
        }

    }

    private fun notNearZero(x:Double, accuracy: Double): Double{
        val xb = BigDecimal((x - 1)/(x + 1));
        var xm = xb
        val acc = BigDecimal(accuracy * 0.1)
        var lnX = xb
        var k = 1
        var summand = BigDecimal.ONE
        while (summand > acc) {
            xm *= xb * xb
            summand = xm / BigDecimal(2 * k + 1)
            lnX += summand
            k++
        }
        return 2*lnX.toDouble()
    }

    private fun nearZero(x:Double, accuracy: Double): Double{
        val xb = BigDecimal(x - 1)
        var xm = xb
        val acc = BigDecimal(accuracy * 0.1)
        var lnX = xb
        var k = BigDecimal.ONE
        var summand = BigDecimal.ONE
        var sig = BigDecimal.ONE
        while (summand > acc || summand < -acc) {
            sig = -sig
            k++
            xm *= xb
            summand = sig * xm / k
            lnX += summand
        }
        return lnX.toDouble()
    }
}