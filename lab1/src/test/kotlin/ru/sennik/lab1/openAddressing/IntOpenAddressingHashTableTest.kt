package ru.sennik.lab1.openAddressing

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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
            listOf(Pair(7, "7"), null, null, Pair(3, "3")),
            map.getList()
        )

        Assertions.assertThrows(NotFoundKeyException::class.java) { map.get(1) }
        Assertions.assertThrows(NotFoundKeyException::class.java) { map.delete(1) }

        Assertions.assertEquals("3", map.get(3))
        map.delete(3)
    }

    @Test
    fun `Create table with negative initialSize failed`() {
        Assertions.assertThrows(
            IllegalArgumentException::class.java,
            { IntOpenAddressingHashTableImpl<String>(75, -1) },
        "initialSize must be more or equal to 1"
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 101])
    fun `Create table with fillFactor isn't percent failed`(fillFactor: Int) {
        Assertions.assertThrows(
            IllegalArgumentException::class.java,
            { IntOpenAddressingHashTableImpl<String>(fillFactor, 10) },
            "fillFactor is percent and must be between 0 and 100"
        )
    }

    @Test
    fun `Create table with fillFactor of initialSize less than one failed`() {
        Assertions.assertThrows(
            IllegalArgumentException::class.java,
            { IntOpenAddressingHashTableImpl<String>(10, 3) },
            "fillFactor of initialSize must be minimum 1"
        )
    }

    // put one elem
    @Test
    fun `Put in table elem, then elem display in list`() {
        // arrange
        val table = IntOpenAddressingHashTableImpl<String>(75, 4)

        // act
        table.put(4, "0")

        // assert
        Assertions.assertIterableEquals(
            listOf(Pair(4, "0"), null, null, null),
            table.getList()
        )
    }
    // put repeat key - already then hash isn't free
    @Test
    fun `Put in table two elem with same key, then second elem has offset`() {
        // arrange
        val table = IntOpenAddressingHashTableImpl<String>(75, 4)

        // act
        table.put(4, "0")
        table.put(4, "4")

        // assert
        Assertions.assertIterableEquals(
            listOf(Pair(4, "0"), Pair(4, "4"), null, null),
            table.getList()
        )
    }
    // put busy key with go around
    @Test
    fun `Put in table elem with key place busy and not free place until end, then elem in start`() {
        // arrange
        val table = IntOpenAddressingHashTableImpl<String>(100, 4)

        // act
        table.put(2, "2")
        table.put(6, "6")
        table.put(3, "3")

        // assert
        Assertions.assertIterableEquals(
            listOf(Pair(3, "3"), null, Pair(2, "2"), Pair(6, "6")),
            table.getList()
        )
    }

    // find simple key
    @Test
    fun `In table elem which key in its position, then elem found successful`() {
        // arrange
        val table = IntOpenAddressingHashTableImpl<String>(75, 4)
        val key = 0
        val value = "0"
        table.put(key, value)

        // act
        val elem = table.get(key)

        // assert
        Assertions.assertEquals(value, elem)
    }
    // find key with offset
    @Test
    fun `In table two elem with same key, then found first elem`() {
        // arrange
        val table = IntOpenAddressingHashTableImpl<String>(75, 4)
        val key = 0
        val value = "0"

        table.put(key, value)
        table.put(key, "another $value")

        // act
        val elem = table.get(key)

        // assert
        Assertions.assertEquals(value, elem)
    }
    // find key with offset with go around
    @Test
    fun `In table elem in position less than its key, then elem found successful`() {
        // arrange
        val initSize = 4
        val table = IntOpenAddressingHashTableImpl<String>(75, initSize)
        val key = initSize - 1
        val value = "3"

        table.put(key + initSize, "another $value")
        table.put(key, value)

        // act
        val elem = table.get(key)

        // assert
        Assertions.assertEquals(value, elem)
    }

    // not found key
    @Test
    fun `In table key place busy by another elem, then elem found failed`() {
        // arrange
        val initSize = 4
        val table = IntOpenAddressingHashTableImpl<String>(75, initSize)
        val key = 0
        val value = "0"

        table.put(key + initSize, value)

        // act && assert
        Assertions.assertThrows(
            NotFoundKeyException::class.java,
            { table.get(key) },
            "Elem with key=$key not found"
        )
    }

    // delete simple key
    @Test
    fun `In table elem which key in its position, then delete elem successful`() {
        // arrange
        val table = IntOpenAddressingHashTableImpl<String>(75, 4)
        val key = 0
        table.put(key, "0")

        // act
        table.delete(key)

        // assert
        Assertions.assertIterableEquals(
            listOf(null, null, null, null),
            table.getList()
        )
    }
    // delete key offset
    @Test
    fun `In table elem in position less than its key, then delete first elem`() {
        // arrange
        val initSize = 4
        val table = IntOpenAddressingHashTableImpl<String>(75, initSize)
        val key = initSize - 1
        table.put(key + initSize, "0")
        table.put(key, "1")

        // act
        table.delete(key)

        // assert
        Assertions.assertIterableEquals(
            listOf(null, Pair(key, "1"), null, null),
            table.getList()
        )
    }
    // delete key offset with go around
    @Test
    fun `In table two elems with same key, then delete first elem`() {
        // arrange
        val table = IntOpenAddressingHashTableImpl<String>(75, 4)
        val key = 0
        table.put(key, "0")
        table.put(key, "1")

        // act
        table.delete(key)

        // assert
        Assertions.assertIterableEquals(
            listOf(null, Pair(key, "1"), null, null),
            table.getList()
        )
    }

    // delete not found
    @Test
    fun `In table key place busy by another elem, then delete elem failed`() {
        // arrange
        val initSize = 4
        val table = IntOpenAddressingHashTableImpl<String>(75, initSize)
        val key = 0
        val value = "0"

        table.put(key + initSize, value)

        // act && assert
        Assertions.assertThrows(
            NotFoundKeyException::class.java,
            { table.delete(key) },
            "Elem with key=$key not found"
        )
    }

    // put key after delete

    @Test
    fun `Fill table to fillFactor, then table increases`() {
        // arrange
        val initSize = 4
        val table = IntOpenAddressingHashTableImpl<String>(75, initSize)

        (1..initSize - 2).map { initSize }.plus(initSize * 2).forEach {
            // act
            table.put(0, "0")

            // assert
            Assertions.assertEquals(it, table.getList().size)
        }
    }

}
