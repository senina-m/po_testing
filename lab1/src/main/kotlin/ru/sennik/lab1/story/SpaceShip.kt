package ru.sennik.lab1.story

import java.lang.RuntimeException

data class SpaceShip (var name: String, var destinationPoint: String){
    private var passengers = mutableSetOf<Human>()
    private var radio : Radio = Radio()

    fun addPerson(person: Human){
        if(!passengers.contains(person)) throw RuntimeException("$person is already on a board of ${this.name}")
        passengers.add(person)
    }

    fun removePerson(person: Human){
        if(!passengers.contains(person)) throw RuntimeException("No $person on a board of ${this.name}")
        passengers.remove(person)
    }

    fun startListenRadio(human: Human){
        if(!passengers.contains(human)) throw RuntimeException("No $human on a board of ${this.name}")
        human.addObjectToNotifyMove(this.radio)
    }

    fun turnOfRadio(human: Human){
        if(!passengers.contains(human)) throw RuntimeException("No $human on a board of ${this.name}")
        human.unsubscribeFromNotifyMove(this.radio)
    }
}