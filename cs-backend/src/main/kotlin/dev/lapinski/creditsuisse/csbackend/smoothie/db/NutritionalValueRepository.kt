package dev.lapinski.creditsuisse.csbackend.smoothie.db

import org.springframework.data.repository.CrudRepository

interface NutritionalValueRepository : CrudRepository<DbNutritionalValue, Long> {
}