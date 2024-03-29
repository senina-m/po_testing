package ru.sennik.lab2

import ru.sennik.lab2.exception.NotValidAccuracyException

import java.io.FileWriter
import java.io.IOException

interface Formula {
    fun count(x: Double, accuracy: Double): Double {
        if (accuracy >= 1 || accuracy <= 0) {
            throw NotValidAccuracyException(accuracy)
        }
        return Double.NaN
    }

    fun toCSV(xStart: Double, xStop: Double, step: Double, accuracy: Double, fileOut: String) {
        FileWriter(fileOut).use { it.write("") }
        FileWriter(fileOut, true).use { it.write("x,res\n") }
        var x = xStart
        while (x <= xStop) {
            val res = count(x, accuracy)
            try {
                FileWriter(fileOut, true).use { it.write("$x,$res\n") }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            x += step
        }
    }
}