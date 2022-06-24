package com.learning.mockk.services

import com.learning.mockk.domain.entities.Direction.NORTH
import com.learning.mockk.domain.entities.Gear.FIRST
import com.learning.mockk.domain.entities.Outcome.OK
import com.learning.mockk.domain.services.CarService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

internal class CarServiceTest: FunSpec({
    context("Driving tests") {

        test("Should go north") {
            val service = mockk<CarService>()
            every { service.drive(NORTH) } returns OK

            val result = service.drive(NORTH)
            result shouldBe OK

            verify { service.drive(NORTH) }
            confirmVerified(service)
        }

        test("Should change gear") {
            val service = mockk<CarService>()
            every { service.changeGear(FIRST) } returns OK

            val result = service.changeGear(FIRST)
            result shouldBe OK

            verify { service.changeGear(FIRST) }
            confirmVerified(service)
        }
    }
})