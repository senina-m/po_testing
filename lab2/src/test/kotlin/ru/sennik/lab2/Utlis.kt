package ru.sennik.lab2

import org.junit.jupiter.params.provider.Arguments
import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.argThat
import org.mockito.ArgumentMatchers.doubleThat
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito
import java.io.File
import kotlin.math.PI

/**
 * @author Natalia Nikonova
 */
class DoubleRangeTwoPiRoundMatcher(private val example: Double) : ArgumentMatcher<Double> {
    override fun matches(argument: Double?): Boolean {
        if (argument == null) return false
        val normArgument = argument % (PI * 2)
        return (example - 0.1 <= normArgument) && (normArgument <= example + 0.1)
    }

}

inline fun <reified T:Formula> getMockFunction(filename: String): T {
    val mockT = Mockito.mock(T::class.java)
    File(filename).forEachLine { line ->
        val data = line.split(";")
        Mockito.`when`(mockT.count(doubleThat(DoubleRangeTwoPiRoundMatcher(data[0].toDouble())), anyDouble())).thenReturn(data[1].toDouble())
    }
    return mockT
}

fun getValues(filename: String): List<Arguments> {
    val result = mutableListOf<Arguments>()
    File(filename).forEachLine { line ->
        val data = line.split(";")
        result.add(Arguments.of(data[0].toDouble()))
    }
    return result
}

fun getArgsAndValues(filename: String): List<Arguments> {
    val result = mutableListOf<Arguments>()
    File(filename).forEachLine { line ->
        val data = line.split(";")
        result.add(Arguments.of(data[0].toDouble(), data[1].toDouble()))
    }
    return result
}