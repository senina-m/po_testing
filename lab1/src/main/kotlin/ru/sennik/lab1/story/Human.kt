package ru.sennik.lab1.story

class Human (private var name: String){
    private var objectsToNotifyMove = mutableSetOf<ObjectToNotifyMove>()

    fun move(){
        objectsToNotifyMove.forEach{obj -> obj.moveHappened()}
    }

    fun addObjectToNotifyMove(obj: ObjectToNotifyMove){
        objectsToNotifyMove.add(obj)
    }

    fun unsubscribeFromNotifyMove(obj: ObjectToNotifyMove){
        objectsToNotifyMove.remove(obj)
    }

    override fun toString(): String {
        return "Human('$name')"
    }
}