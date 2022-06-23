package com.learning.mockk.services

import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import io.mockk.mockk
import com.learning.mockk.domain.entities.Outcome.OK
import com.learning.mockk.domain.entities.Direction.NORTH
import com.learning.mockk.domain.entities.Gear.FIRST
import com.learning.mockk.domain.services.CarService
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.verify

internal class CarServiceTest: FunSpec({
    context("Driving tests") {
        val service = mockk<CarService>()
        test("Should go north") {
            every { service.drive(NORTH) } returns OK

            val result = service.drive(NORTH)
            result shouldBe OK

            verify { service.drive(NORTH) }
            clearAllMocks()
        }

        test("Should change gear") {
            every { service.changeGear(FIRST) } returns OK

            val result = service.changeGear(FIRST)
            service.drive(NORTH)
            result shouldBe OK

            verify { service.changeGear(FIRST) }
            clearAllMocks()
        }
    }
})