package com.example.TaskApp.services

import com.example.TaskApp.repositories.TaskRepository
import com.example.TaskApp.repositories.TaskStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.Duration

@Service
class TaskNotificationService(
    private val taskRepository: TaskRepository  
) {
    
    @Scheduled(fixedRate = 60000)
    @Transactional
    fun checkTasks() {
        val now = LocalDateTime.now()
        
        // Шаг 1: Находим все задачи со статусом WAITING
        val waitingTasks = taskRepository.findAll()
            .filter { it.status == TaskStatus.WAITING }
        

        for (task in waitingTasks) {
            if (task.dueDateTime.isBefore(now)) {

                task.status = TaskStatus.LATE
                taskRepository.save(task)
            }
        }

        val lateTasks = taskRepository.findAll()
            .filter { it.status == TaskStatus.LATE }
        
        for (task in lateTasks) {
            val overdueMinutes = Duration.between(task.dueDateTime, now).toMinutes()
            println("Просрочено: '${task.name}' (${overdueMinutes} минут)")
        }
    }
}