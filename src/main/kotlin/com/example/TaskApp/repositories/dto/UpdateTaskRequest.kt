package com.example.TaskApp.dto

import com.example.TaskApp.repositories.TaskStatus
import java.time.LocalDateTime
import org.springframework.format.annotation.DateTimeFormat

data class UpdateTaskRequest(
    val name: String? = null,
    val description: String? = null,
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    val dueDateTime: LocalDateTime? = null,
    val status: TaskStatus? = null
)