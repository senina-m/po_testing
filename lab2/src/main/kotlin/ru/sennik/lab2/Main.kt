package ru.sennik.lab2
import ru.sennik.lab2.log.Ln
import kotlin.math.*

fun main() {
    val value = 2.1
    val ln = Ln()
    println(ln.count(value, 0.001))
    println(ln(value))
}
