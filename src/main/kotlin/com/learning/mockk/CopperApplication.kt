package com.learning.mockk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CopperApplication

fun main(args: Array<String>) {
	runApplication<CopperApplication>(*args)
}
