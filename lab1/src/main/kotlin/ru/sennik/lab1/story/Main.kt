package ru.sennik.lab1.story


fun main(){
    val zafod = Human("Зафод")
    val spaceShip = SpaceShip("Золотое Сердце")
    spaceShip.turnOnRadio(zafod)

    zafod.move()
    zafod.move()
    zafod.move()
}