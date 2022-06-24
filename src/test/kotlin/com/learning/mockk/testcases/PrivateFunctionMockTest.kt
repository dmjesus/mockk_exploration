package com.learning.mockk.testcases

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.spyk
import io.mockk.verifySequence

class Car {
    fun drive() = accelerate()
    private fun accelerate() = "going faster"
}

class PrivateFunctionMockTest : FunSpec({
    context("Private Mocking") {
        test("Should mock private function") {
            val mock = spyk<Car>(recordPrivateCalls = true)

            every { mock["accelerate"]() } returns "going not so fast"
            mock.drive() shouldBe "going not so fast"

            verifySequence {
                mock.drive()
                mock["accelerate"]()
            }
        }
    }
})