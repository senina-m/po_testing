package ru.sennik.lab1.math

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI

/**
 * @author Natalia Nikonova
 */
class CosTest {
    private val defaultAccuracy = 0.001

    @ParameterizedTest
    @ValueSource(doubles = [
        0.0,
        PI / 6, PI / 3,
        PI / 2,
        2 * PI / 3, 5 * PI / 6,
        PI,
        7 * PI / 6, 4 * PI / 3,
        3 * PI / 2,
        5 * PI / 3, 11 * PI / 6,
        2 * PI,
    ])
    fun checkFunction(value: Double) {
        Assertions.assertEquals(
            kotlin.math.cos(value),
            cos(value, defaultAccuracy),
            defaultAccuracy
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.00001, 0.99999])
    fun checkAllowedAccuracy(accuracy: Double) {
        assertDoesNotThrow { cos(1.0, accuracy) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 1.0])
    fun checkNotAllowedAccuracy(accuracy: Double) {
        assertThrows<RuntimeException> { cos(1.0, accuracy) }
    }
}
