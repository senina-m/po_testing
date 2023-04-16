package ru.sennik.lab2.log

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import ru.sennik.lab2.exception.NotValidAccuracyException
import kotlin.math.E

class LnTest {
    private val defaultAccuracy = 0.001

    @ParameterizedTest
    @ValueSource(doubles = [0.11, 0.23, 0.53, 0.6, 0.7, 0.8, 0.9, 1.0, 1.6, 2.0, 5.0, 10.4])
    fun checkFunction(value: Double) {
        val log = Ln()
        Assertions.assertEquals(
            kotlin.math.log(value, E),
            log.count(value, defaultAccuracy),
            defaultAccuracy
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.00001, 0.99999])
    fun checkAllowedAccuracy(accuracy: Double) {
        val ln = Ln()
        assertDoesNotThrow { ln.count(1.0, accuracy) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 1.0])
    fun checkNotAllowedAccuracy(accuracy: Double) {
        val ln = Ln()
        assertThrows<NotValidAccuracyException> { ln.count(1.0, accuracy) }
    }
}
