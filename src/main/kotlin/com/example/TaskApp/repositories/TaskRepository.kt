package com.example.TaskApp.dataSources

import com.example.TaskApp.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID


@Repository
interface TaskRepository : JpaRepository<Task, UUID> {
    fun findByDueDate(dueDate: java.time.LocalDate): List<Task>
}