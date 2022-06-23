package com.learning.mockk.testcases

import com.learning.mockk.domain.entities.Chevette
import com.learning.mockk.domain.entities.Direction.NORTH
import com.learning.mockk.domain.entities.Outcome.OK
import com.learning.mockk.domain.entities.fly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify

class StaticMockTest: FunSpec({
    context("Static Mocking") {
        test("Should mock static function") {
            mockkStatic(Chevette::fly)

            every { Chevette.fly(NORTH) } returns OK

            Chevette.fly(NORTH) shouldBe OK

            verify {
                Chevette.fly(NORTH)
            }

            unmockkStatic(Chevette::fly)
        }
    }
})