package dev.lapinski.creditsuisse.csbackend.smoothie

import java.math.BigDecimal
import java.time.LocalDateTime

enum class NutritionalValueUnit {
    G_PER_100ML,
    G_PER_100G,
    DAILY_INTAKE_PERCENT
}

data class NutritionalValue(
    val id: Long?,
    val smoothieId: Long?,
    val addedAt: LocalDateTime,
    val name: String,
    val value: BigDecimal,
    val unit: NutritionalValueUnit
)
