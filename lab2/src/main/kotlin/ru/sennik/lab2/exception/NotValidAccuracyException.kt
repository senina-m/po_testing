package ru.sennik.lab2.exception

/**
 * @author Natalia Nikonova
 */
class NotValidAccuracyException(x: Double) : RuntimeException("Accuracy has to be from 0 to 1, found: $x")
