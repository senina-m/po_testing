package ru.sennik.lab1.openAddressing

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author Natalia Nikonova
 */
class IntOpenAddressingHashTableTest {
    @Test
    fun tryInvokeFunctions() {
        val map  = IntOpenAddressingHashTableImpl<String>(75, 4)

        map.put(3, "3")
        map.put(7, "7")
        Assertions.assertIterableEquals(
            mutableListOf(Pair(7, "7"), null, null, Pair(3, "3")),
            map.getList()
        )

        Assertions.assertThrows(NotFoundKeyException::class.java) { map.get(1) }
        Assertions.assertThrows(NotFoundKeyException::class.java) { map.delete(1) }

        Assertions.assertEquals("3", map.get(3))
        map.delete(3)
    }
}
