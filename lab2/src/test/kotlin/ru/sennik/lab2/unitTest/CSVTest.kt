package ru.sennik.lab2.unitTest

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import ru.sennik.lab2.DifficultFunction
import ru.sennik.lab2.log.Ln
import ru.sennik.lab2.log.Log10
import ru.sennik.lab2.log.Log2
import ru.sennik.lab2.log.Log3
import ru.sennik.lab2.log.Log5
import ru.sennik.lab2.trigonom.Cos
import ru.sennik.lab2.trigonom.Cot
import ru.sennik.lab2.trigonom.Csc
import ru.sennik.lab2.trigonom.Sin
import ru.sennik.lab2.trigonom.Tan
import java.io.File

/**
 * @author Natalia Nikonova
 */
class CSVTest {
    @Test
    fun csvDiffFunc() {
        val filename = "output.csv"
        val sin = Sin()
        val cos = Cos(sin)
        val cot = Cot(sin, cos)
        val tan = Tan(sin, cos)
        val csc = Csc(sin)
        val ln = Ln()
        val log10 = Log10(ln)
        val log5 = Log5(ln)
        val log3 = Log3(ln)
        val log2 = Log2(ln)
        val difFun = DifficultFunction(
            sin, cos, csc, tan, cot,
            ln, log2, log3, log5, log10
        )
        difFun.toCSV(1.0, 4.0, 0.1, 0.01, filename)

        assertDoesNotThrow { File(filename) }
    }

    @Test
    fun csvSin() {
        val filename = "output.csv"
        val sin = Sin()
        sin.toCSV(-3.0, 3.0, 0.1, 0.01, filename)
        assertDoesNotThrow { File(filename) }
    }

    @Test
    fun csvCos() {
        val filename = "output.csv"
        val sin = Sin()
        val cos = Cos(sin)
        cos.toCSV(-3.0, 3.0, 0.1, 0.01, filename)
        assertDoesNotThrow { File(filename) }
    }

    @Test
    fun csvLn() {
        val filename = "output.csv"
        val ln = Ln()
        ln.toCSV(1.0, 5.0, 0.1, 0.01, filename)
        assertDoesNotThrow { File(filename) }
    }

    @Test
    fun csvLog2() {
        val filename = "output.csv"
        val ln = Ln()
        val log = Log2(ln)
        log.toCSV(1.0, 5.0, 0.1, 0.01, filename)
        assertDoesNotThrow { File(filename) }
    }

    @Test
    fun csvLog3() {
        val filename = "output.csv"
        val ln = Ln()
        val log = Log3(ln)
        log.toCSV(1.0, 5.0, 0.1, 0.01, filename)
        assertDoesNotThrow { File(filename) }
    }

    @Test
    fun csvLog5() {
        val filename = "output.csv"
        val ln = Ln()
        val log = Log5(ln)
        log.toCSV(1.0, 5.0, 0.1, 0.01, filename)
        assertDoesNotThrow { File(filename) }
    }

    @Test
    fun csvLog10() {
        val filename = "output.csv"
        val ln = Ln()
        val log = Log10(ln)
        log.toCSV(1.0, 5.0, 0.1, 0.01, filename)
        assertDoesNotThrow { File(filename) }
    }
}
