package com.learning.mockk.testcases

import com.learning.mockk.domain.entities.Car
import com.learning.mockk.domain.entities.Gear.FIRST
import com.learning.mockk.domain.entities.Outcome.OK
import com.learning.mockk.domain.services.CarService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.justRun
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class JUnit5MockingTest {

    @MockK
    lateinit var carService1: CarService

    @RelaxedMockK
    lateinit var carService2: CarService

    @MockK(relaxUnitFun = true)
    lateinit var carService3: CarService

    @SpyK
    var carService4 = CarService(Car())

    @Test
    fun car1MockTest() {
        justRun { carService1.unitFunctionExample() }
        every { carService1.changeGear(any()) } returns OK // matcher example

        carService1.unitFunctionExample()
        carService1.changeGear(FIRST).also {
            assertEquals(OK, it)
        }
    }

    @Test
    fun car2RelaxedMockTest() {
        // Do not need to set carService1.unitFunctionExample()
        // every { carService2.changeGear(any()) } returns OK

        carService2.unitFunctionExample() // no missing answer
        carService2.changeGear(FIRST).also {
            assertNotNull(it)
        }
    }

    @Test
    fun car3RelaxedFunMockTest() {
        // justRun { carService1.unitFunctionExample() }
        every { carService3.changeGear(any()) } returns OK // matcher example

        carService3.unitFunctionExample() // no missing answer
        carService3.changeGear(FIRST).also {
            assertEquals(it, OK)
        }
    }

    @Test
    fun car4SpyKMockTest() {
        justRun { carService4.unitFunctionExample() }

        carService4.unitFunctionExample()
        carService4.run {
            assertEquals(OK, changeGear(FIRST))
            assertEquals(FIRST, car.gear)
        }
    }
}