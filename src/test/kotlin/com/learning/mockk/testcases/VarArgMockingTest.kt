package com.learning.mockk.testcases

import com.learning.mockk.domain.entities.VarArgClass
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

class VarArgMockingTest : FunSpec({
    context("Varargs Mocking") {
        test("Should mock function with var arg argument") {
            val obj = mockk<VarArgClass>()

            every { obj.argsFunction(1, 2, 3, *varargAllInt { it == 7 }) } returns 3

            obj.argsFunction(1, 2, 3, 7) shouldBe 3
            obj.argsFunction(1, 2, 3, 7, 7) shouldBe 3
            obj.argsFunction(1, 2, 3, 7, 7, 7) shouldBe 3

            verify(exactly = 1) { obj.argsFunction(1, 2, 3, 7) }
            verify(exactly = 1) { obj.argsFunction(1, 2, 3, 7, 7) }
            verify(exactly = 1) { obj.argsFunction(1, 2, 3, 7, 7, 7) }

            confirmVerified(obj)
            unmockkAll()
        }

        test("Should mock function with var arg argument receiving any integer") {
            val obj = mockk<VarArgClass>()

            every { obj.argsFunction(*anyIntVararg()) } returns 3

            obj.argsFunction(1, 7) shouldBe 3
            obj.argsFunction(1, 2, 7) shouldBe 3
            obj.argsFunction(1, 2, 3, 7) shouldBe 3

            verify(exactly = 1) { obj.argsFunction(1, 7) }
            verify(exactly = 1) { obj.argsFunction(1, 2, 7) }
            verify(exactly = 1) { obj.argsFunction(1, 2, 3, 7) }

            confirmVerified(obj)
            unmockkAll()
        }
    }
})