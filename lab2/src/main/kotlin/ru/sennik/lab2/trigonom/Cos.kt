package ru.sennik.lab2.trigonom

import ru.sennik.lab2.Formula
import kotlin.math.PI

open class Cos(
    private val sin: Sin
) : Formula {
    override fun count(x: Double, accuracy: Double): Double {
        super.count(x, accuracy)
        val r = sin.count((PI / 2 + x), accuracy)
        println("cos($x) -> sin(${PI / 2 + x}) -> $r")
        return  r
    }
}