package ru.sennik.lab3

import java.io.FileInputStream
import java.io.IOException
import java.util.*

object ConfProperties {
    private var fileInputStream: FileInputStream? = null
    private var PROPERTIES: Properties? = null

    init {
        try {
            //указание пути до файла с настройками
            fileInputStream = FileInputStream("src/test/resources/conf.properties")
            PROPERTIES = Properties()
            PROPERTIES!!.load(fileInputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            //обработка возможного исключения (нет файла и т.п.)
        } finally {
            if (fileInputStream != null) try {
                fileInputStream!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * метод для возврата строки со значением из файла с настройками
     */
    fun getProperty(key: String?): String {
        return PROPERTIES!!.getProperty(key)
    }
}