package ru.sennik.lab2

import java.math.BigDecimal
import kotlin.math.PI
class Cos(private var x: Double, private var  accuracy: Double) : Formula {
    override fun count(): Double {
        val sin = Sin(PI - x, accuracy)
        return sin.count()
    }
}