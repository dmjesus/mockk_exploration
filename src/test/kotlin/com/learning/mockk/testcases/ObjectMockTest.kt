package com.learning.mockk.testcases

import com.learning.mockk.domain.entities.Chevette
import com.learning.mockk.domain.entities.Direction.NORTH
import com.learning.mockk.domain.entities.Gear.SECOND
import com.learning.mockk.domain.entities.Outcome.OK
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

class ObjectMockTest: FunSpec({
    context("Static Mocking") {
        test("Should mock static object") {
            mockkObject(Chevette)

            every { Chevette.drive(NORTH) } returns OK
            every { Chevette.changeGear(SECOND) } returns OK

            Chevette.drive(NORTH) shouldBe OK
            Chevette.changeGear(SECOND) shouldBe OK

            verifyAll {
                Chevette.drive(NORTH)
                Chevette.changeGear(SECOND)
            }

            confirmVerified(Chevette)

            unmockkObject(Chevette)

            shouldThrow<RuntimeException> {
                Chevette.changeGear(SECOND)
            }
        }
    }
})