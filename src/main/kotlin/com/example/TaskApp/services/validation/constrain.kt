package com.example.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

// Список запрещенных слов
val words = listOf("говно", "залупа", "пенис", "хер", "С++")

class CustomValidator : ConstraintValidator<CustomConstraint, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {

            if (value == null) {
                return true
            }

        val containsAny = words.any { word -> value.contains(word, ignoreCase = true) }

        return !containsAny
    }
}