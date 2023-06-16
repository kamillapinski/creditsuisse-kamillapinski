package dev.lapinski.creditsuisse.csbackend.smoothie

import java.time.LocalDateTime

data class Smoothie(
    val id: Long?,
    val addedAt: LocalDateTime,
    val name: String,
    val description: String,
    val nutritionalValues: Set<NutritionalValue>,
    val availableInStock: Int
)
