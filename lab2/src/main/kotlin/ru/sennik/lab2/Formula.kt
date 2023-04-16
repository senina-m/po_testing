package ru.sennik.lab2

interface Formula {
    fun count(x: Double, accuracy: Double): Double
    fun toCSV(): Double
}