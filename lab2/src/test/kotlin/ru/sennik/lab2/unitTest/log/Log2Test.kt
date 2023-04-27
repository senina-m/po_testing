package ru.sennik.lab2.unitTest.log

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import ru.sennik.lab2.exception.FunctionNotExistsException
import ru.sennik.lab2.exception.NotValidAccuracyException
import ru.sennik.lab2.log.Ln
import ru.sennik.lab2.log.Log2

class Log2Test {
    private val defaultAccuracy = 0.001
    private val defaultLn = Ln()

    @ParameterizedTest
    @ValueSource(doubles = [0.1, 0.2, 0.53, 0.6, 0.7, 0.8, 0.9, 1.0, 1.6, 2.0, 5.0, 10.0])
    fun checkFunction(value: Double) {
        val log = Log2(defaultLn)
        Assertions.assertEquals(
            kotlin.math.log(value, 2.0),
            log.count(value, defaultAccuracy),
            defaultAccuracy
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, -1.0])
    fun checkNotExistFunction(x: Double) {
        val log = Log2(defaultLn)
        assertThrows<FunctionNotExistsException> { log.count(x, defaultAccuracy) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.00001, 0.99999])
    fun checkAllowedAccuracy(accuracy: Double) {
        val ln = Log2(defaultLn)
        assertDoesNotThrow { ln.count(1.0, accuracy) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 1.0])
    fun checkNotAllowedAccuracy(accuracy: Double) {
        val ln = Log2(defaultLn)
        assertThrows<NotValidAccuracyException> { ln.count(1.0, accuracy) }
    }
}
