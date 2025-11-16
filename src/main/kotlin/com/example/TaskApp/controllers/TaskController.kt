package com.example.TaskApp.controllers

import com.example.TaskApp.dto.CreateCommentRequest
import com.example.TaskApp.repositories.Task
import com.example.TaskApp.services.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.example.TaskApp.dto.CreateTaskRequest
import com.example.TaskApp.dto.UpdateTaskRequest
import org.springframework.web.bind.annotation.PatchMapping
import java.util.UUID

@RestController
@RequestMapping("/tasks")
class TaskController(
    private val service: TaskService
) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(e:NoSuchElementException) : ResponseEntity<String>{
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e:IllegalArgumentException) : ResponseEntity<String>{
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @GetMapping
    fun getTasks(): List<Task> {
        return service.getTasks()
    }

    @DeleteMapping("/{taskId}")
    fun deleteTask(@PathVariable taskId: UUID) {
        service.delete(taskId)
    }

    @PostMapping
    fun createTask(@RequestBody request: CreateTaskRequest): Task {
        return service.createTask(request)
    }

    @PatchMapping("/{taskId}")
    fun updateTask(
    @PathVariable taskId: UUID,
    @RequestBody request: UpdateTaskRequest
    ): Task {
    return service.updateTask(taskId, request)
    }

    @PostMapping("/{taskId}/comments")
    fun addComment(
        @PathVariable taskId: UUID,
        @RequestBody request: CreateCommentRequest
    ): Task {
        return service.addCommentToTask(taskId, request.text)
    }

}