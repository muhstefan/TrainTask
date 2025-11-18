package com.example.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

// Список запрещенных слов
val words = listOf("слово1", "слово2", "слово3")

class CustomValidator : ConstraintValidator<CustomConstraint, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {

            if (value == null) {
                return true
            }

        val containsAny = words.any { word -> value.contains(word, ignoreCase = true) }

        return !containsAny
    }
}