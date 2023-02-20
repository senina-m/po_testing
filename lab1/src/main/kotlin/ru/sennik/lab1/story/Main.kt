package ru.sennik.lab1.story


fun main() {
    val zafod = Human("Зафод")
    val spaceShip = SpaceShip("Золотое Сердце", "Земля")
    spaceShip.destinationPoint = "Марс"
    spaceShip.addPerson(zafod)
    spaceShip.startListenRadio(zafod)

    zafod.move()
    zafod.move()
    zafod.move()
}