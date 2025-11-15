package com.example.TaskApp.services
import com.example.TaskApp.dto.UpdateTaskRequest
import com.example.TaskApp.repositories.TaskRepository
import com.example.TaskApp.dto.CreateTaskRequest
import com.example.TaskApp.model.Task
import org.springframework.stereotype.Service
import java.util.UUID
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class TaskService(
    private val repository: TaskRepository
) {
    
    fun getTasks(): List<Task> {
        return repository.findAll()
    }
    
    fun getTaskById(id: UUID): Task {
        return repository.findById(id)
            .orElseThrow { NoSuchElementException("No Task found with id $id") }
    }
    
    fun delete(id: UUID) {
        if (!repository.existsById(id)) {
            throw NoSuchElementException("No Task found with id $id")
        }
        repository.deleteById(id)
    }
    
    fun createTask(request: CreateTaskRequest): Task {
        val task = Task()
        task.name = request.name
        task.description = request.description
        task.dueDateTime = request.dueDateTime 
        return repository.save(task)
    }

    fun getTasksGroupedByDate(): Map<LocalDate, List<Task>> {
        return repository.findAll()
            .groupBy { it.dueDateTime.toLocalDate() }
            .toSortedMap()
    }

    fun getTasksByDate(date: LocalDate): List<Task> {
        return repository.findAll()
            .filter { it.dueDateTime.toLocalDate() == date } 
    }

    fun updateTask(id: UUID, request: UpdateTaskRequest): Task {
        val task = getTaskById(id) 
        request.name?.let { task.name = it }
        request.description?.let { task.description = it }
        request.dueDateTime?.let { task.dueDateTime = it }
        request.status?.let { task.status = it }
        return repository.save(task) 
    }
}