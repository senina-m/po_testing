package ru.sennik.lab1.openAddressing

/**
 * @author Natalia Nikonova
 */
class IntOpenAddressingHashTableImpl(
   private val initialSize: Int,
   private val fillFactor: Int,
) : IntOpenAddressingHashTable {
   init {
      if (initialSize < 1) { throw IllegalArgumentException("initialSize must be more or equal to 1") }
      if (fillFactor > 100 || fillFactor < 0) {
         throw IllegalArgumentException("fillFactor is percent and must be between 0 and 100")
      }
      if (initialSize.toDouble() / 100 * fillFactor < 1) {
         throw IllegalArgumentException("fillFactor if initialSize must be minimum 1")
      }
   }

   private val table = MutableList(initialSize) { _ -> 0 }
   private var fullCount = 0
   override fun put(elem: Int) {
      TODO("Not yet implemented")
   }

   override fun delete(elem: Int) {
      TODO("Not yet implemented")
   }

   override fun contains(elem: Int): Boolean {
      TODO("Not yet implemented")
   }

   override fun getList(): List<Int?> {
      TODO("Not yet implemented")
   }

   private fun isFillEnough() {
      val currentSize = table.size

   }
}
