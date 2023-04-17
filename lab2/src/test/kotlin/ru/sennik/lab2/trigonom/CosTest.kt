package ru.sennik.lab2.trigonom

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import ru.sennik.lab2.exception.NotValidAccuracyException
import ru.sennik.lab2.getMockTrigFunction
import ru.sennik.lab2.getValues

class CosTest {
    private val defaultAccuracy = 0.01
    private val defaultSin = getMockTrigFunction<Sin>(filename)

    @ParameterizedTest
    @MethodSource("cosValuesMethodSource")
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
        assertThrows<NotValidAccuracyException> { cos.count(1.0, accuracy) }
    }

    companion object {
        private const val filename = "sin_to_cos.csv"

        @JvmStatic
        fun cosValuesMethodSource() = getValues(filename)
    }
}
