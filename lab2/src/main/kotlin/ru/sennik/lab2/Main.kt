package ru.sennik.lab2
import ru.sennik.lab2.log.Ln

fun main() {
    val value = 2.1
    val ln = Ln()
//    println(ln.count(value, 0.001))
//    println(ln(value))
    ln.toCSV(3.0, 5.0, 0.5, 0.01, "test.txt")
}
