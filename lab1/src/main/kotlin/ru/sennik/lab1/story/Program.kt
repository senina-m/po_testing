package ru.sennik.lab1.story

class Program(
    val name: String,
    val type: KindOfProgram
) : Playable {
    override fun play(){
        print("$name: ")
        val numWords = (1..10).random()
        for (i in 0 until numWords){
            print("${type.words[(0 until type.words.size).random()]} ")
        }
        println(".")
    }
}