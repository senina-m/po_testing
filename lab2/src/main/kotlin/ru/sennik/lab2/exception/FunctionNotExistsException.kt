package ru.sennik.lab2.exception

/**
 * @author Natalia Nikonova
 */
class FunctionNotExistsException(x: Double, funName: String, cause: Throwable? = null) :
    RuntimeException("$funName doesn't exist on x=$x", cause)
