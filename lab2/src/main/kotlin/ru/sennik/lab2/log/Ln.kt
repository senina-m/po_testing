package ru.sennik.lab2.log

import ru.sennik.lab2.Formula
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class Ln() : Formula {
    override fun count(x: Double, accuracy: Double): Double {
        val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))
        if (accuracy >= 1 || accuracy <= 0) {
            throw RuntimeException("Accuracy has to be from 0 to 1!")
        }
        val x = BigDecimal(x - 1)
        var xm = x
        val acc = BigDecimal(accuracy * 0.0001)
        var lnX = x
        var k = BigDecimal.ONE
        var summand = BigDecimal.ONE
        var sig = BigDecimal.ONE
        while (summand > acc || summand < -acc) {
            sig = -sig
            k++
            xm *= x
            summand = sig * xm / k
            println("sig=$sig xm=${xm} k=$k sum=${summand}")
            lnX += summand
        }
        println("k=$k, xm=${xm}, sum=${summand}")
        return lnX.toDouble()
    }

    override fun toCSV(): Double {
        TODO("Not yet implemented")
    }
}