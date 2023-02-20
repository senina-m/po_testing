package ru.sennik.lab1.story

class Human constructor(name: String){
    var name : String = ""
    lateinit var objectsToNotifyMove: Set<ObjectToNotifyMove>

    fun move(){
//        objectsToNotifyMove.forEach(obj.move)
    }

    fun addObjectToNotifyMove(obj: ObjectToNotifyMove){
//        objectsToNotifyMove.add(obj)
    }

}