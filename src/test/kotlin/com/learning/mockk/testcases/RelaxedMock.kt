package com.learning.mockk.testcases

import com.learning.mockk.domain.entities.Direction.NORTH
import com.learning.mockk.domain.entities.Direction.SOUTH
import com.learning.mockk.domain.entities.Outcome.OK
import com.learning.mockk.domain.services.CarService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class RelaxedMock: FunSpec({
    context("Relaxed mocking") {
        test("Should mock relaxed") {
            val service = mockk<CarService>(relaxed = true)

            every { service.drive(NORTH) } returns OK
            // every { service.drive(SOUTH) } returns OK

            service.drive(NORTH) shouldBe OK
            service.drive(SOUTH) shouldNotBe OK

            verify {
                service.drive(NORTH)
                service.drive(SOUTH) //no answer found for: CarService(#1).drive(SOUTH)
            }

            clearAllMocks()
        }
    }
})