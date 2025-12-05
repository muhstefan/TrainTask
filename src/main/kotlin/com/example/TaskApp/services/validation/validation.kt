package com.example.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CustomValidator::class])
annotation class CustomConstraint(
    val message: String = "Матерится нельзя!",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)