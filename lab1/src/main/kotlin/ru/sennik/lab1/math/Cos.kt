package ru.sennik.lab1.math

import java.math.BigDecimal

fun cos(x: Double, accuracy: Double): Double {
    if (accuracy >= 1 || accuracy <= 0) {
        throw RuntimeException("Accuracy has to be from 0 to 1!")
    }
    val xb = BigDecimal(x)
    val xb2 = xb * xb
    val acc = BigDecimal(accuracy * 0.1)

    var cosX = BigDecimal.ONE
    var f =  BigDecimal.ONE
    var crx = BigDecimal.ONE
    var n = BigDecimal.ONE
    var i = 1

    var summand = crx / f
    while (summand >= acc) {
        if (i  > 20) break

        f *= n * (n + BigDecimal.ONE)
        crx *= xb2
        summand = crx / f

        n += BigDecimal(2)
        i += 1
        if (i % 2 == 0) {
            cosX -= summand
        } else {
            cosX += summand
        }
    }

    return cosX.toDouble()
}
