package ru.sennik.lab2

import java.lang.Math.abs
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class Log(private var x: Double, private var accuracy: Double) : Formula {

    //(-1)^k * x^(k+1)/(k+1)
    override fun count(): Double {
        val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH))
        if (accuracy >= 1 || accuracy <= 0) {
            throw RuntimeException("Accuracy has to be from 0 to 1!")
        }
        val x = x - 1
        var xm = BigDecimal(x)
        val acc = BigDecimal(accuracy * 0.1)
        var lnX = BigDecimal(x)
        var k = 1
        var summand = BigDecimal.ONE
        while (summand >= acc) {
            k++
            xm *= BigDecimal(x)
            summand = xm / BigDecimal(k + 1)
            println("k=$k, xm=${df.format(xm).toDouble()}, sum=${df.format(summand).toDouble()}")

            if (k % 2 == 0) {
                lnX += summand
            } else {
                lnX -= summand
            }
        }

        return lnX.toDouble()
    }

//    override fun count(): Double {
//        var s = 0;
//        var n = 1;
//        var an = x;
//        while (kotlin.math.abs(an) > accuracy){
//            s += an;
//            n++;
//            an*= -x*(n-1)/n;
//        }
//        return s;
//    }
}