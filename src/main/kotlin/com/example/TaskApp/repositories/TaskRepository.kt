package com.example.TaskApp.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query  
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaskRepository : JpaRepository<Task, UUID> {
    
    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.comments WHERE t.id = :id")
    fun findByIdWithComments(id: UUID): Task?
}