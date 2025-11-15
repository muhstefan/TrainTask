package com.example.TaskApp.dto

import com.example.TaskApp.model.TaskStatus
import java.time.LocalDateTime  
import org.springframework.format.annotation.DateTimeFormat

data class UpdateTaskRequest(
    val name: String? = null,
    val description: String? = null,
    val dueDateTime: LocalDateTime? = null, 
    val status: TaskStatus? = null
)

data class CreateTaskRequest(
    val name: String,
    val description: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")  
    val dueDateTime: LocalDateTime  
)