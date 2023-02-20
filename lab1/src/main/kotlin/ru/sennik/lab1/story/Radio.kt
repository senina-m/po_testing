package ru.sennik.lab1.story

class Radio : ObjectToNotifyMove() {
    lateinit var currentProgram: Program
    var programs : Set<Program> = HashSet<Program>()
    var loudness: Int = 20
    fun runProgram(){

    }
    fun goToNextProgram(){

    }

    fun move(){
        print("some program-dependent staff")
    }
}