package ru.sennik.lab2
import kotlin.math.*

fun main() {
    val values = listOf(
        /*//-2 * PI,
        -11 * PI / 6,
        -5 * PI / 3,
        //- 3 * PI / 2,
        -4 * PI / 3,
        -5 * PI / 4,
        -89 * PI / 72,
        -7 * PI / 6,
        -9 * PI / 8,
        -5 * PI / 6,
        -7 * PI / 8,
        -55 * PI / 72,
        -3 * PI / 4,
        -2 * PI / 3,
        -PI / 3,
        -PI / 6*/
        0.5,
        0.7,
        1.0,
        1.2,
        1.5,
        1.7,
        1.9,
        2.0,
        2.15,
        2.3,
        2.5,
        2.6,
        2.8,
        3.0,
        3.15,
        3.5,
        4.0,
        4.3,
        4.5,
        4.75,
        5.0,
        6.0,
        7.0
    )
    for (value in values) {
        println("%.5f;%.5f".format(value, log(value, 10.0)))
    }
}
