package ru.sennik.lab2
import kotlin.math.*

fun main() {
    val values = listOf(
        /*PI / 6,
        PI / 3,
        PI / 2,
        2 * PI / 3,
        5 * PI / 6,
        7 * PI / 6, 4 * PI / 3,
        3 * PI / 2,
        5 * PI / 3, 11 * PI / 6,*/
        PI / 6,
        PI / 3,
        PI / 2,
        2 * PI / 3,
        5 * PI / 6
    )
    for (value in values) {
        println("%.5f;%.5f".format(value, cos(value)))
    }
}
