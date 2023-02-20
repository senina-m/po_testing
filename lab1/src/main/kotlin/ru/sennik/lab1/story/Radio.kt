package ru.sennik.lab1.story

class Radio : ObjectToNotifyMove {
    private var programs : Set<Program> = mutableSetOf(Program("Earth news", KindOfProgram.NEWS))
    private var nextProgram: Iterator<Program> = programs.iterator()
    private var currentProgram: Program = nextProgram.next()
    var loudness: Int = 20
    fun runProgram(){
        currentProgram.play()
    }
    fun goToNextProgram(){
        currentProgram = nextProgram.next()
    }

    override fun moveHappened() {
        val program = nextProgram.next()
        program.play()
    }
}