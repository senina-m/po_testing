package ru.sennik.lab3

/**
 * @author Natalia Nikonova
 */
fun repeatUntilSuccess(action: () -> Unit) {
    while( runCatching { action.invoke() }.isFailure ) {}
}
