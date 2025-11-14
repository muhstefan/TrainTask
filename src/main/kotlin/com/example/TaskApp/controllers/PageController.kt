package com.example.TaskApp.controllers

import com.example.TaskApp.dto.CreateTaskRequest
import com.example.TaskApp.services.TaskService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.time.LocalDate
import java.util.*


@Controller
class HomeController {
    @GetMapping("/")
    fun redirectRoot(): String {
        return "redirect:/page/"
    }
}

@Controller
@RequestMapping("/page")
class PageController(
    private val service: TaskService
) {
    
    @GetMapping("/")
    fun index(): String {
        return "index"
    }
    
    @GetMapping("/create")
    fun createForm(): String {
        return "create"
    }
    
    @PostMapping("/create")
    fun createTask(
        @ModelAttribute request: CreateTaskRequest,
        redirectAttributes: RedirectAttributes
    ): String {
        val task = service.createTask(request)
        redirectAttributes.addFlashAttribute("message", "Задача создана! ID: ${task.id}")
        return "redirect:/page/"
    }


    @GetMapping("/tasks-by-date")
    fun tasksByDate(model: Model): String {
        model.addAttribute("tasksByDate", service.getTasksGroupedByDate())
        return "tasks-by-date"
    }

    @GetMapping("/tasks-by-date", params = ["date"])
    fun tasksByDate(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
        date: LocalDate,
        model: Model
    ): String {
        model.addAttribute("date", date)
        model.addAttribute("tasks", service.getTasksByDate(date))
        return "tasks-by-day"
    }

    @DeleteMapping("/tasks/{taskId}")
    fun deleteTask(
        @PathVariable taskId: UUID,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) returnDate: LocalDate?
    ): String {
        service.delete(taskId)
        return returnDate?.let { "redirect:/page/tasks-by-date?date=$it" } 
            ?: "redirect:/page/tasks-by-date"
    }
}
