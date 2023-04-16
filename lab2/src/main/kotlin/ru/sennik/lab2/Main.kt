package ru.sennik.lab2
import ru.sennik.lab2.log.Ln

fun main() {
    val values = listOf(
        0.0,
        PI / 6,
        PI / 3,
        PI / 2,
        2 * PI / 3,
        5 * PI / 6,
        PI,
        7 * PI / 6, 4 * PI / 3,
        3 * PI / 2,
        5 * PI / 3, 11 * PI / 6,
        2 * PI,
    )
    for (value in values) {
        println("%.5f;%.5f".format(value, sin(value)))
    }
    val value = 2.1
    val ln = Ln()
//    println(ln.count(value, 0.001))
//    println(ln(value))
    ln.toCSV(3.0, 5.0, 0.5, 0.01, "test.txt")
}
