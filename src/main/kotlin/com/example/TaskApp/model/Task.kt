package com.example.TaskApp.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

enum class TaskStatus {
    WAITING,
    COMPLETE
}

@Table(name = "tasks")
@Entity
open class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    open var id: UUID? = null

    @Column(name = "name", nullable = false)
    open var name: String = ""

    @Column(name = "description", nullable = false)
    open var description: String = ""

    @Column(name = "due_date", nullable = false) 
    lateinit var dueDate: LocalDate  

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    open var status: TaskStatus = TaskStatus.WAITING


}