package ru.sennik.lab1.story

class SpaceShip constructor(name: String){
    lateinit var name : String //TODO: how to write constructors
    lateinit var destinationPoint: String
    var passangers : Set<Human> = HashSet<Human>()
    lateinit var radio : Radio

    fun addPerson(person: Human){
//        passangers.add(Human)
    }

    fun removePerson(person: Human){
    }

    fun addDestinationPoint(person: Human){
    }

    fun changeDestinationPoint(person: Human){
    }

    fun turnOnRadio(human: Human){
        human.addObjectToNotifyMove(this.radio)
    }
}