package ru.sennik.lab1.story

class Radio : ObjectToNotifyMove {
    private var programs : Set<Program> = mutableSetOf(Program("Earth news", KindOfProgram.NEWS), Program("Music", KindOfProgram.MUSIC))
    private var nextProgram: Iterator<Program> = programs.iterator()
    private var currentProgram: Program = nextProgram.next()
    var loudness: Int = 20
    fun runProgram(){
        currentProgram.play()
    }
    private fun goToNextProgram() : Program{
        if (nextProgram.hasNext()) {
            currentProgram = nextProgram.next()
        } else {
            nextProgram = programs.iterator()
            currentProgram = nextProgram.next()
        }
        return currentProgram
    }

    override fun moveHappened() {
        val program = goToNextProgram()
        program.play()
    }
}