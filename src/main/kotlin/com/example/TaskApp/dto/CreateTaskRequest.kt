package com.example.TaskApp.dto

import com.example.TaskApp.model.TaskStatus
import java.time.LocalDate
import org.springframework.format.annotation.DateTimeFormat

data class UpdateTaskRequest(
    val name: String? = null,
    val description: String? = null,
    val dueDate: LocalDate? = null,
    val status: TaskStatus? = null
)

data class CreateTaskRequest(
    val name: String,
    val description: String,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val dueDate: LocalDate
)