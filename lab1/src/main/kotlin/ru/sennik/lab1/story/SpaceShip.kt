package ru.sennik.lab1.story

import kotlin.RuntimeException

class SpaceShip (var name: String, var destinationPoint: String) {
    private val passengers = mutableSetOf<Human>()
    private val radio = Radio()

    fun addPerson(person: Human){
        if (!passengers.add(person)) throw RuntimeException("$person is already on a board of $name")
    }

    fun removePerson(person: Human){
        if (!passengers.contains(person)) throw RuntimeException("No $person on a board of $name")
        runCatching { person.unsubscribeFromNotifyMove(radio) }
        passengers.remove(person)
    }

    fun getShipPassengers() = passengers.toSet()

    fun startListenRadio(human: Human){
        if (!passengers.contains(human)) throw RuntimeException("No $human on a board of $name")
        human.addObjectToNotifyMove(radio)
    }

    fun stopListenRadio(human: Human){
        if (!passengers.contains(human)) throw RuntimeException("No $human on a board of $name")
        runCatching { human.unsubscribeFromNotifyMove(radio) }.onFailure { throw RuntimeException("$human not listen radio") }
    }

    internal fun getCurrentRadioProgram() = radio.getCurrentProgram()
}
