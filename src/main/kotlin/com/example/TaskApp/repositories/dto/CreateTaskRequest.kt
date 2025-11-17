package com.example.TaskApp.dto

import com.example.TaskApp.repositories.TaskStatus
import java.time.LocalDateTime  
import org.springframework.format.annotation.DateTimeFormat
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateTaskRequest(
    @field:NotBlank(message = "Название задачи не может быть пустым")
    val name: String,
    
    @field:NotBlank(message = "Описание задачи не может быть пустым")
    val description: String,
    
    @field:NotNull(message = "Дата выполнения обязательна")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")  
    val dueDateTime: LocalDateTime  
)