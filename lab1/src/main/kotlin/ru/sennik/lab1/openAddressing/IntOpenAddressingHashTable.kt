package ru.sennik.lab1.openAddressing

/** Хэш-таблица, работающая с неотрицательными значениями типа Int.
 * Поддерживает операции put, delete и contains.
 * Также есть метод getList для тестирования модуля.
 * Позволяет повторы значений (?)
 *
 * @author Natalia Nikonova
 */
interface IntOpenAddressingHashTable {
   /**
    * Добавление элемента в таблицу
    * @param elem - добавляемый элемент
    */
   fun put(elem: Int)

   /**
    * Удаление элемента из таблицы
    * @param elem - удаляемый элемент
    * @throws NotFoundException - если элемент не существует
    */
   fun delete(elem: Int)

   /**
    * Проверяет вхождение элемента в таблицу
    * @param elem - элемент, вхождение которого проверяется
    * @return - true если присутствует, false если нет
    */
   fun contains(elem: Int): Boolean

   /**
    * Возвращает текущее состояние внутренней памяти структуры.
    * Null значение означает что ячейка свободна
    * @return - список с хранимыми значениями
    */
   fun getList(): List<Int?>
}
