package ru.sennik.lab1.openAddressing

/**
 * @author Natalia Nikonova
 */
class NotFoundException(message: String) : RuntimeException(message)

class InternalException(message: String, cause: Throwable?) : RuntimeException(message, cause)

class NotFoundKeyException(key: Int) : RuntimeException("Elem with key=$key not found")
