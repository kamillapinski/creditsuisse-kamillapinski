package dev.lapinski.creditsuisse.csbackend.smoothie

data class ListedSmoothie(
    val id: Long,
    val name: String,
    val description: String,
    val availableInStock: Int
)
