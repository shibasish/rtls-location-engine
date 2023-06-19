package com.circulariot.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@SpringBootApplication(exclude = [WebMvcAutoConfiguration::class])
@ComponentScan(excludeFilters = [ComponentScan.Filter(
	type = FilterType.ASSIGNABLE_TYPE,
	value = [WebMvcAutoConfiguration::class]
)])
class BackendApplication

fun main(args: Array<String>) {
	runApplication<BackendApplication>(*args)
}
