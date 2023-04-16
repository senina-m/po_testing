package ru.sennik.lab2.log

import ru.sennik.lab2.Formula

class Log3(private var ln: Ln) : Formula {
    override fun count(x: Double, accuracy: Double): Double {
        println("ln($x)=${ln.count(x, accuracy)} ln(3)=${ln.count(3.0, accuracy)}")
        return ln.count(x, accuracy)/ln.count(3.0, accuracy)
    }

    override fun toCSV(): Double {
        TODO("Not yet implemented")
    }
}