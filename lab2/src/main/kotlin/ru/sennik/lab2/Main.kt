package ru.sennik.lab2
import ru.sennik.lab2.trigonom.Sin
import kotlin.math.PI

fun main() {
    val sin = Sin()
    println(sin.count(0.5235963267948966, 0.001))
    val x = -3.88336
    val accuracy = 0.001
    val r = sin.count(PI / 2 + x, accuracy)
    println("$x -> sin(${PI / 2 + x}) -> $r")
}
//то, что в файле -3.88336;-0.73728 // -0.73727570572201318
//-3.88336 -> sin(-2.3125636732051036) -> -0.70711
//-3.88336 -> sin(-2.3125636732051036) -> -0.70711
//-3.88336 -> sin(-2.3125636732051036) -> -0.70711

//expected: <-1.90718> but was: <-2.0491081033282375>
//Expected :-1.90718
//Actual   :-2.0491081033282375

//-0.78539;-0.70710
//-0.82903;-0.73727