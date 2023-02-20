package ru.sennik.lab1.story

class Program (private var name: String, private var type: KindOfProgram) : Playable{
    override fun play(){
        print("${this.name}: ")
        val numWords = (0..10).random()
        for (i in 1.. numWords){
            print("${this.type.words[(0 until this.type.words.size).random()]} ")
        }
        println(".")
    }
}