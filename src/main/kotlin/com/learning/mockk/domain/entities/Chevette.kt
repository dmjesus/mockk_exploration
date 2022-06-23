package com.learning.mockk.domain.entities

import com.learning.mockk.domain.entities.Outcome.OK

object Chevette {

    fun changeGear(gear: Gear): Outcome {
        throw RuntimeException("Gear box problem")
    }

    fun changeDirection(direction: Direction): Outcome {
        throw RuntimeException("Steering box problem")
    }

    fun drive(direction: Direction) = OK.also { println("Chevetim vai que vai") }
}

fun Chevette.fly(direction: Direction): Outcome {
    throw RuntimeException("Try again, please! I believe I can fly")
}