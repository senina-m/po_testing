package ru.sennik.lab1.story

/**
 * @author Natalia Nikonova
 */
fun getProgramPlayPattern(name: String, type: KindOfProgram) =
    "$name: (${type.words.joinToString(separator = " |")} ){1,10}\\.\r\n"