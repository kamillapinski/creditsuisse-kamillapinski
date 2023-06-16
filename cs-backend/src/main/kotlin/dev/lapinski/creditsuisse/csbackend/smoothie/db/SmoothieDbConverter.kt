package dev.lapinski.creditsuisse.csbackend.smoothie.db

import dev.lapinski.creditsuisse.csbackend.smoothie.NutritionalValue
import dev.lapinski.creditsuisse.csbackend.smoothie.Smoothie
import org.springframework.stereotype.Component

@Component
class SmoothieDbConverter {
    fun dbSmoothieToDomain(db: DbSmoothie): Smoothie = Smoothie(
        id = db.id,
        addedAt = db.addedAt!!,
        name = db.name!!,
        description = db.description!!,
        availableInStock = db.availableInStock!!,
        nutritionalValues = db.nutritionalValues.map(this::dbNutritionalValueToDomain).toSet()
    )

    fun dbNutritionalValueToDomain(db: DbNutritionalValue): NutritionalValue = NutritionalValue(
        id = db.id,
        addedAt = db.addedAt,
        value = db.value,
        smoothieId = db.smoothie!!.id,
        name = db.name,
        unit = db.unit
    )

    fun smoothieDomainToDb(smoothie: Smoothie): DbSmoothie = DbSmoothie(
        id = smoothie.id,
        addedAt = smoothie.addedAt,
        name = smoothie.name,
        description = smoothie.description,
        availableInStock = smoothie.availableInStock,
        nutritionalValues = emptySet()
    )

    fun nutritionalValueToDb(nutritionalValue: NutritionalValue, dbSmoothie: DbSmoothie) = DbNutritionalValue(
        id = nutritionalValue.id,
        addedAt = nutritionalValue.addedAt,
        name = nutritionalValue.name,
        value = nutritionalValue.value,
        unit = nutritionalValue.unit,
        smoothie = dbSmoothie
    )
}