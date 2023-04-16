package ru.sennik.lab2.log

import ru.sennik.lab2.Formula

class Log10(private var ln: Ln) : Formula {
    override fun count(x: Double, accuracy: Double): Double {
        return ln.count(x, accuracy)/ln.count(10.0, accuracy);
    }
}