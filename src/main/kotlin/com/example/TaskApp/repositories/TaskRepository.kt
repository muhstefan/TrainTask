package com.example.TaskApp.repositories

import com.example.TaskApp.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID
import java.time.LocalDate

@Repository
interface TaskRepository : JpaRepository<Task, UUID> {
   
}