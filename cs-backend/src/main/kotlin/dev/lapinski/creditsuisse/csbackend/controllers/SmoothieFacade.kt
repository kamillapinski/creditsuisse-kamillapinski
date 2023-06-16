package dev.lapinski.creditsuisse.csbackend.controllers

import dev.lapinski.creditsuisse.csbackend.smoothie.ListedSmoothieDtoConverter
import dev.lapinski.creditsuisse.csbackend.smoothie.SmoothieDetailsDtoConverter
import dev.lapinski.creditsuisse.csbackend.smoothie.SmoothieService
import org.springframework.stereotype.Component

@Component
class SmoothieFacade(
    private val smoothieService: SmoothieService,
    private val smoothieDetailsDtoConverter: SmoothieDetailsDtoConverter,
    private val listedSmoothieDtoConverter: ListedSmoothieDtoConverter
) {
    fun listSmoothies(): Collection<ListedSmoothieDto> =
        smoothieService.listSmoothies().map(listedSmoothieDtoConverter::domainToDto)

    fun getSmoothieById(id: Long): SmoothieDetailsDto? {
        val smoothie = smoothieService.findSmoothieById(id)

        return if (smoothie != null) {
            smoothieDetailsDtoConverter.domainToDto(smoothie)
        } else null
    }

    fun addSmoothie(detailsDto: SmoothieDetailsDto) {
        //TODO: add validation for null ID
        insertOrUpdateSmoothie(detailsDto)
    }

    fun updateSmoothie(detailsDto: SmoothieDetailsDto) {
        //TODO: add validation for non-null ID
        insertOrUpdateSmoothie(detailsDto)
    }

    private fun insertOrUpdateSmoothie(detailsDto: SmoothieDetailsDto) {
        smoothieService.saveSmoothie(
            smoothieDetailsDtoConverter.dtoToDomain(detailsDto)
        )
    }
}