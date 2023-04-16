package ru.sennik.lab2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import ru.sennik.lab2.log.Ln
import ru.sennik.lab2.trigonom.Sin
import kotlin.math.E

class LogTest {
    private val defaultAccuracy = 0.001
    private val defaultBase = 2

    @ParameterizedTest
    @ValueSource(doubles = [0.1, 0.2, 0.5, 0.7, 1.0, 1.5, 2.0, 10.0])
    fun checkFunction(value: Double) {
        val log = Sin(value, defaultAccuracy)
        Assertions.assertEquals(
            kotlin.math.log(value, E),
            log.count(),
            defaultAccuracy
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.00001, 0.99999])
    fun checkAllowedAccuracy(accuracy: Double) {
        val ln = Ln(1.0, accuracy)
        assertDoesNotThrow { ln.count(x, accuracy) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 1.0])
    fun checkNotAllowedAccuracy(accuracy: Double) {
        val ln = Ln(1.0, accuracy)
        assertThrows<RuntimeException> { ln.count(x, accuracy) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.5, 0.8, 2.0, 4.0, 10.0])
    fun checkBase(accuracy: Double) {
        val ln = Ln(1.0, accuracy)
        assertThrows<RuntimeException> { ln.count(x, accuracy) }
    }
}
