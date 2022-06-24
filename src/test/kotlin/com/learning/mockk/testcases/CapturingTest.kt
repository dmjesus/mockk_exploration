package com.learning.mockk.testcases

import com.learning.mockk.domain.entities.Direction.NORTH
import com.learning.mockk.domain.entities.Direction.SOUTH
import com.learning.mockk.domain.entities.Outcome.RECORDED
import com.learning.mockk.domain.services.CarService
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CapturingTest {

    @MockK
    lateinit var carService: CarService

    private val slot = slot<Int>()
    private val list = mutableListOf<Int>()

    @Test
    fun testCapturingFeature() {
        every {
            carService.recordTelemetry(
                speed = capture(slot), // makes mock match calls with any value for `speed` and record it in a slot
                direction = NORTH // makes mock and capturing only match calls with specific `direction`. Use `any()` to match calls with any `direction`
            )
        } answers {
            println(slot.captured)
            RECORDED
        }

        every {
            carService.recordTelemetry(
                speed = capture(list),
                direction = SOUTH
            )
        } answers {
            println(list)
            RECORDED
        }

        carService.recordTelemetry(speed = 15, direction = NORTH) // prints 15
        carService.recordTelemetry(speed = 16, direction = SOUTH) // prints 16
        carService.recordTelemetry(speed = 17, direction = SOUTH) // prints 16

        verify(exactly = 3) {
            carService.recordTelemetry(
                speed = match { listOf(15, 16, 17).contains(it) },
                direction = any()
            )
        }

        confirmVerified(carService)
    }
}