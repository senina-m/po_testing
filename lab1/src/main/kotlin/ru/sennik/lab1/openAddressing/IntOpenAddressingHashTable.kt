package ru.sennik.lab1.openAddressing

/** Хэш-таблица, ключем может выступать Int,
 * значением - объект класса, которым параметризован экземпляр таблицы.
 * Поддерживает операции put, delete и get.
 * Позволяет повторы ключей. Не синхронизирована
 *
 * @author Natalia Nikonova
 */
interface IntOpenAddressingHashTable<T> {
   /**
    * Добавление элемента по ключу
    * @param elem - добавляемый элемент
    */
   fun put(key: Int, elem: T)

   /**
    * Удаление элемента по ключу
    * @param key - ключ удаляемого элемента
    * @throws NotFoundKeyException - если ключ не найден
    */
   fun delete(key: Int)

   /**
    * Получение элемента по ключу
    * (если ключ встречается несколько раз, возвращается элемент по его первому вхождению)
    * @param key - ключ, по которому идет поиск элемента
    * @return - элемент, лежащий по этому ключу
    * @throws NotFoundKeyException - если ключ не найден
    */
   fun get(key: Int): T
}
