package ru.sennik.lab2.log

import ru.sennik.lab2.Formula

class Log5(private var ln: Ln) : Formula {
    override fun count(x: Double, accuracy: Double): Double {
        super.count(x, accuracy)
        return ln.count(x, accuracy * 0.1) / ln.count(5.0, accuracy * 0.1)
    }
}