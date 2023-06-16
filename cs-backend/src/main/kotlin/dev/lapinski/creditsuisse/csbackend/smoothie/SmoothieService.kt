package dev.lapinski.creditsuisse.csbackend.smoothie

import dev.lapinski.creditsuisse.csbackend.smoothie.db.NutritionalValueRepository
import dev.lapinski.creditsuisse.csbackend.smoothie.db.SmoothieDbConverter
import dev.lapinski.creditsuisse.csbackend.smoothie.db.SmoothieRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SmoothieService(
    private val smoothieRepository: SmoothieRepository,
    private val nutritionalValueRepository: NutritionalValueRepository,
    private val smoothieDbConverter: SmoothieDbConverter
) {
    @Transactional
    fun listSmoothies(): Collection<ListedSmoothie> =
        smoothieRepository.findAllOrderByAddedAtDesc().map {
            ListedSmoothie(
                id = it.id!!,
                name = it.name!!,
                description = it.description!!,
                availableInStock = it.availableInStock!!
            )
        }

    @Transactional
    fun findSmoothieById(id: Long): Smoothie? =
        smoothieRepository.findFullById(id)?.let(smoothieDbConverter::dbSmoothieToDomain)

    @Transactional
    fun saveSmoothie(smoothie: Smoothie) {
        val dbSmoothie = smoothieRepository.save(smoothieDbConverter.smoothieDomainToDb(smoothie))
        val dbNutritionalValues = smoothie.nutritionalValues.map {smoothieDbConverter.nutritionalValueToDb(it, dbSmoothie)}
        nutritionalValueRepository.saveAll(dbNutritionalValues)
    }
}