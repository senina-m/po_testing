package ru.sennik.lab1.story

class Human(private val name: String) {
    private var objectsToNotifyMove = mutableSetOf<ObjectToNotifyMove>()

    fun move(){
        objectsToNotifyMove.forEach { obj -> obj.moveHappened() }
    }

    fun addObjectToNotifyMove(obj: ObjectToNotifyMove){
        if (!objectsToNotifyMove.add(obj)) throw RuntimeException("$this already notifies $obj")
    }

    fun unsubscribeFromNotifyMove(obj: ObjectToNotifyMove){
        if (!objectsToNotifyMove.remove(obj)) throw RuntimeException("$this not notifies $obj")
    }

    override fun toString(): String {
        return "Human(name=$name)"
    }

    internal fun getObjectsToNotify() = objectsToNotifyMove.toSet()
}
