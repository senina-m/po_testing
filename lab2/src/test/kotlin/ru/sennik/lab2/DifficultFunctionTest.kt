package ru.sennik.lab2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.doubleThat
import org.mockito.Mockito
import ru.sennik.lab2.exception.FunctionNotExistsException
import ru.sennik.lab2.exception.NotValidAccuracyException
import ru.sennik.lab2.log.*
import ru.sennik.lab2.trigonom.*
import kotlin.math.PI

/**
 * @author Natalia Nikonova
 */
class DifficultFunctionTest {

    @ParameterizedTest
    @MethodSource("funValuesMethodSource")
    fun checkFunctionExist(x: Double, result: Double) {
        val difFun = DifficultFunction(defaultSin, defaultCos, defaultCsc, defaultTan, defaultCot,
            defaultLn, defaultLog2, defaultLog3, defaultLog5, defaultLog10)
        Assertions.assertEquals(
            result,
            difFun.count(x, defaultAccuracy),
            defaultAccuracy
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [-PI * 2, -3 * PI / 2, -PI, -PI / 2, 0.0])
    fun checkFunctionNotExist(value: Double) {
        val difFun = DifficultFunction(defaultSin, defaultCos, defaultCsc, defaultTan, defaultCot,
            defaultLn, defaultLog2, defaultLog3, defaultLog5, defaultLog10)
        assertThrows<FunctionNotExistsException> { difFun.count(value, defaultAccuracy) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.00001, 0.99999])
    fun checkAllowedAccuracy(accuracy: Double) {
        val difFun = DifficultFunction(defaultSin, defaultCos, defaultCsc, defaultTan, defaultCot,
            defaultLn, defaultLog2, defaultLog3, defaultLog5, defaultLog10)
        assertDoesNotThrow { difFun.count(1.0, accuracy) }
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 1.0])
    fun checkNotAllowedAccuracy(accuracy: Double) {
        val difFun = DifficultFunction(defaultSin, defaultCos, defaultCsc, defaultTan, defaultCot,
            defaultLn, defaultLog2, defaultLog3, defaultLog5, defaultLog10)
        assertThrows<NotValidAccuracyException> { difFun.count(1.0, accuracy) }
    }

    companion object {
        private const val sinFilename = "sin_to_dif.csv"
        private const val cosFilename = "cos_to_dif.csv"
        private const val cscFilename = "csc_to_dif.csv"
        private const val tanFilename = "tan_to_dif.csv"
        private const val cotFilename = "cot_to_dif.csv"

        private const val lnFilename = "ln_to_dif.csv"
        private const val log2Filename = "log2_to_dif.csv"
        private const val log3Filename = "log3_to_dif.csv"
        private const val log5Filename = "log5_to_dif.csv"
        private const val log10Filename = "log10_to_dif.csv"

        private const val resultFilename = "dif_fun_res.csv"

        private val defaultAccuracy = 0.01
        private val defaultSin = getMockTrigFunction<Sin>(sinFilename)
        private val defaultCos = getMockTrigFunction<Cos>(cosFilename)
        private val defaultCsc = getMockTrigFunction<Csc>(cscFilename)
        private val defaultTan = getMockTrigFunction<Tan>(tanFilename)
        private val defaultCot = getMockTrigFunction<Cot>(cotFilename)
        private val defaultLn = getMockLogFunction<Ln>(lnFilename)
        private val defaultLog2 = getMockLogFunction<Log2>(log2Filename)
        private val defaultLog3 = getMockLogFunction<Log3>(log3Filename)
        private val defaultLog5 = getMockLogFunction<Log5>(log5Filename)
        private val defaultLog10 = getMockLogFunction<Log10>(log10Filename)

        @JvmStatic
        fun funValuesMethodSource() = getArgsAndValues(resultFilename)

        @JvmStatic
        @BeforeAll
        fun setUpNotValidArg() {
            Mockito.`when`(
                defaultCsc.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(-2 * PI)),
                    anyDouble()
                )
            ).thenThrow(FunctionNotExistsException::class.java)
            Mockito.`when`(
                defaultCsc.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(-PI)),
                    anyDouble()
                )
            ).thenThrow(FunctionNotExistsException::class.java)
            Mockito.`when`(
                defaultCsc.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(0.0)),
                    anyDouble()
                )
            ).thenThrow(FunctionNotExistsException::class.java)

            Mockito.`when`(
                defaultTan.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(-3 * PI / 2)),
                    anyDouble()
                )
            ).thenThrow(FunctionNotExistsException::class.java)
            Mockito.`when`(
                defaultTan.count(doubleThat(DoubleRangeTwoPiRoundMatcher(-PI / 2)), anyDouble())
            ).thenThrow(FunctionNotExistsException::class.java)

            Mockito.`when`(
                defaultCot.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(-2 * PI)),
                    anyDouble()
                )
            ).thenThrow(FunctionNotExistsException::class.java)
            Mockito.`when`(
                defaultCot.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(-PI)),
                    anyDouble()
                )
            )
                .thenThrow(FunctionNotExistsException::class.java)
            Mockito.`when`(
                defaultCot.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(0.0)),
                    anyDouble()
                )
            ).thenThrow(FunctionNotExistsException::class.java)

            Mockito.`when`(
                defaultLn.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(0.0)),
                    anyDouble()
                )
            ).thenThrow(FunctionNotExistsException::class.java)
            Mockito.`when`(
                defaultLog2.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(0.0)),
                    anyDouble()
                )
            ).thenThrow(FunctionNotExistsException::class.java)
            Mockito.`when`(
                defaultLog3.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(0.0)),
                    anyDouble()
                )
            ).thenThrow(FunctionNotExistsException::class.java)
            Mockito.`when`(
                defaultLog5.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(0.0)),
                    anyDouble()
                )
            ).thenThrow(FunctionNotExistsException::class.java)
            Mockito.`when`(
                defaultLog10.count(
                    doubleThat(DoubleRangeTwoPiRoundMatcher(0.0)),
                    anyDouble()
                )
            ).thenThrow(FunctionNotExistsException::class.java)
        }
    }
}
