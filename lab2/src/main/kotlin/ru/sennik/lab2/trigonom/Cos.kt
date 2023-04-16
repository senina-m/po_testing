package ru.sennik.lab2.trigonom

import ru.sennik.lab2.Formula
import kotlin.math.PI
class Cos(private var sin: Sin) : Formula {
    override fun count(x: Double, accuracy: Double): Double {
        return sin.count(PI/2 + x, accuracy)
    }

    override fun toCSV(): Double {
        TODO("Not yet implemented")
    }
}