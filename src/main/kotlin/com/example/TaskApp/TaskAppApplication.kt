package com.example.TaskApp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class TaskAppApplication

fun main(args: Array<String>) {
	runApplication<TaskAppApplication>(*args)
}
