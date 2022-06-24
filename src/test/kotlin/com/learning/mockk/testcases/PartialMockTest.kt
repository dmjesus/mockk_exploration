package com.learning.mockk.testcases

import com.learning.mockk.domain.entities.Car
import com.learning.mockk.domain.entities.Direction.NORTH
import com.learning.mockk.domain.entities.Gear.SECOND
import com.learning.mockk.domain.entities.Outcome.OK
import com.learning.mockk.domain.services.CarService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

class PartialMockTest : FunSpec({
    context("Driving tests") {

        test("Should change car gear in mockk object") {
            val service = mockk<CarService>()
            every { service.car } returns Car()
            every { service.drive(NORTH) } returns OK
            every { service.changeGear(SECOND) } answers { callOriginal() }

            service.drive(NORTH) shouldBe OK
            service.changeGear(SECOND).also { outcome ->
                outcome shouldBe OK
                service.car.gear shouldBe SECOND
            }

            verifyAll {
                service.car
                service.drive(NORTH)
                service.changeGear(SECOND)
            }
            confirmVerified(service)
        }
    }
})