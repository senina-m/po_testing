package ru.sennik.lab1.openAddressing

/** Реализация Хэш-таблицы
 * @param fillFactor - коэффициент заполнения таблицы, по его достижении внутренняя память увлечивается вдвое.
 * Может быть между 0 и 100б рекомендуется значение, близкое к 75
 * @param initialSize - начальная вместимость таблицы. Должна быть больше 1,
 * и так соотносится с коэффициентом заполнения, что можно добавить один элемент, не увеличивая прежде таблицу
 * @author Natalia Nikonova
 */
class IntOpenAddressingHashTableImpl<T>(
   private val fillFactor: Int,
   initialSize: Int,
) : IntOpenAddressingHashTable<T> {
   init {
      if (initialSize < 1) { throw IllegalArgumentException("initialSize must be more or equal to 1") }
      if (fillFactor > 100 || fillFactor <= 0) {
         throw IllegalArgumentException("fillFactor is percent and must be between 0 and 100")
      }
      if (initialSize.toDouble() / 100 * fillFactor < 1) {
         throw IllegalArgumentException("fillFactor of initialSize must be minimum 1")
      }
   }

   private val table = getNullPairList(initialSize)
   private val freeMap = getBoolList(initialSize)
   private var fullCount = 0

   override fun put(key: Int, elem: T) {
      try {
         val index = findAcceptableCell(key, false) { it == null }
         table[index] = Pair(key, elem)
         fullCount += 1
         freeMap[index] = false
         if (isFillEnough()) {
            val currentSize = table.size
            table.addAll(getNullPairList(currentSize))
            freeMap.addAll(getBoolList(currentSize))
         }
      } catch (ex: NotFoundException) {
         throw InternalException("Unexpected error. Please, send a report about it", ex)
      }
   }

   override fun delete(key: Int) {
      try {
         val index = findAcceptableCell(key, true) { it?.first == key }
         table[index] = null
         fullCount -= 1
      } catch (ex: NotFoundException) {
         throw NotFoundKeyException(key)
      }
   }

   override fun get(key: Int): T {
      try {
         val index = findAcceptableCell(key, true) { it?.first == key }
         return table[index]!!.second
      } catch (ex: NotFoundException) {
         throw NotFoundKeyException(key)
      }
   }

   /**
    * Метод для тестирования модуля. Возвращает текущее состояние внутренней памяти структуры.
    * Null значение означает что ячейка свободна
    * @return - список с хранимыми значениями
    */
   internal fun getList(): List<Pair<Int, T>?> {
      return table
   }

   private fun isFillEnough(): Boolean {
      val currentSize = table.size
      val factor = fullCount.toDouble() * 100 / currentSize
      return factor >= fillFactor
   }

   private fun findAcceptableCell(
      key: Int,
      stopIfFree: Boolean,
      checkAcceptable: (Pair<Int, T>?) -> Boolean
   ): Int {
      val tableLen = table.size
      val startIndex = key % tableLen
      var result = startIndex
      while (!checkAcceptable(table[result])) {
         result = (result + 1) % tableLen
         if (result == startIndex || (stopIfFree && freeMap[result])) {
            throw NotFoundException("Can't found acceptable cell for conditions: key=$key, stopIfFree=$stopIfFree")
         }
      }
      return result
   }

   private fun getNullPairList(size: Int) = MutableList<Pair<Int, T>?>(size) { _ -> null }

   private fun getBoolList(size: Int) = MutableList(size) { _ -> true }
}
