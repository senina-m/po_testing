package ru.sennik.lab1.math

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
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
        1.0, PI / 3,
        PI / 2,
        2 * PI / 3, 3.0,
        PI,
        4.0, 4 * PI / 3,
        3 * PI / 2,
        5 * PI / 3, 6.0,
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
    @MethodSource("accuracyTestMethodSource")
    fun checkAllowedAccuracy(accuracy: Double, success: Boolean) {
        if (success) {
            assertDoesNotThrow { cos(1.0, accuracy) }
        } else {
            assertThrows<RuntimeException> { cos(1.0, accuracy) }
        }
    }

   companion object {
       @JvmStatic
       fun accuracyTestMethodSource() = listOf(
           Arguments.of(1, true),
           Arguments.of(0.00001, true),
           Arguments.of(0, false),
           Arguments.of(1.00001, false)
       )
   }
}
