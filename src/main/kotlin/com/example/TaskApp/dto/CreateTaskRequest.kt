package com.example.TaskApp.dto

import java.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat

data class CreateTaskRequest(
    val name: String,
    val description: String,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val dueDate: LocalDate
)