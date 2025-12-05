package com.example.TaskApp.dto

import com.example.TaskApp.repositories.TaskStatus
import com.example.validation.CustomConstraint  // ← Добавить импорт
import java.time.LocalDateTime  
import org.springframework.format.annotation.DateTimeFormat
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size  // ← Добавить импорт

data class CreateTaskRequest(
    @field:NotBlank(message = "Название задачи не может быть пустым")
    @field:Size(min = 5, max = 30, message = "Название должно быть от 5 до 30 символов")
    @field:CustomConstraint(message = "Название содержит МАТЕРКИ")
    val name: String,
    
    @field:NotBlank(message = "Описание задачи не может быть пустым")
    @field:CustomConstraint(message = "Описание содержит МАТЕРКИ")  // ← Убрать "ы" в конце!
    val description: String,
    
    @field:NotNull(message = "Дата выполнения обязательна")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")  
    val dueDateTime: LocalDateTime  
)