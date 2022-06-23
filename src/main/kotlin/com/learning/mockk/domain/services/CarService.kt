package com.learning.mockk.domain.services

import com.learning.mockk.domain.entities.Car
import com.learning.mockk.domain.entities.Direction
import com.learning.mockk.domain.entities.Gear
import com.learning.mockk.domain.entities.Outcome
import com.learning.mockk.domain.entities.Outcome.OK

class CarService(val car: Car) {

    fun drive(newDirection: Direction): Outcome {
        car.direction = newDirection
        return OK
    }

    fun changeGear(newGear: Gear): Outcome {
        car.gear = newGear
        return OK
    }

    fun unitFunctionExample() {
        // do nothing
    }
}