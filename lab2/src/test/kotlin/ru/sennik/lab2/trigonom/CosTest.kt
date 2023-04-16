package ru.sennik.lab2.trigonom

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

import kotlin.math.PI
class CosTest {
    private val defaultAccuracy = 0.001
    private val defaultSin = Sin()

    @ParameterizedTest
    @ValueSource(doubles = [
        0.0,
        PI / 6,
        PI / 3,
        PI / 2,
        2 * PI / 3,
        5 * PI / 6,
        PI,
        7 * PI / 6, 4 * PI / 3,
        3 * PI / 2,
        5 * PI / 3, 11 * PI / 6,
        2 * PI,
    ])
    fun checkFunction(value: Double) {
        val cos = Cos(defaultSin)
        Assertions.assertEquals(
            kotlin.math.cos(value),
            cos.count(value, defaultAccuracy),
            defaultAccuracy
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.00001, 0.99999])
    fun checkAllowedAccuracy(accuracy: Double) {
        val cos = Cos(defaultSin)
        assertDoesNotThrow { cos.count(1.0, accuracy) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 1.0])
    fun checkNotAllowedAccuracy(accuracy: Double) {
        val cos = Cos(defaultSin)
        assertThrows<RuntimeException> { cos.count(1.0, accuracy) }
    }
}
