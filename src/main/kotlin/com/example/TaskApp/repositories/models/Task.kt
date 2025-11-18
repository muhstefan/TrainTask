package com.example.TaskApp.repositories

import com.example.validation.CustomConstraint
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime
import java.util.UUID

enum class TaskStatus {
    WAITING,
    LATE,
    COMPLETE,
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

    @Column(name = "due_date_time", nullable = false) 
    lateinit var dueDateTime: LocalDateTime  

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    open var status: TaskStatus = TaskStatus.WAITING

    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL], orphanRemoval = true)
    open var comments: MutableList<Comment> = mutableListOf()
}
