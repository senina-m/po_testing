package ru.sennik.lab1.story

data class SpaceShip (var name: String, var destinationPoint: String){
    var passangers = mutableSetOf<Human>()
    private var radio : Radio = Radio()

    fun addPerson(person: Human){
        passangers.add(person)
    }

    fun removePerson(person: Human){
        passangers.remove(person)
    }

    fun startListenRadio(human: Human){
        if(!passangers.contains(human)) print("No $human on a board of ${this.name}")
        human.addObjectToNotifyMove(this.radio)
    }

    fun turnOfRadio(human: Human){
        human.unsubscribeFromNotifyMove(this.radio)
    }
}