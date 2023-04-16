package ru.sennik.lab2.log

import ru.sennik.lab2.Formula

class Log2(private var ln: Ln) : Formula {
    override fun count(x: Double, accuracy: Double): Double {
        return ln.count(x, accuracy) / ln.count(2.0, accuracy)
    }
}