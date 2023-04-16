package ru.sennik.lab2

import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import java.io.File

/**
 * @author Natalia Nikonova
 */
inline fun <reified T:Formula> getMockFunction(filename: String): T {
    val mockT = Mockito.mock(T::class.java)
    File(filename).forEachLine { line ->
        val data = line.split(",")
        Mockito.`when`(mockT.count(data[0].toDouble(), any())).thenReturn(data[1].toDouble())
    }
    return mockT
}

