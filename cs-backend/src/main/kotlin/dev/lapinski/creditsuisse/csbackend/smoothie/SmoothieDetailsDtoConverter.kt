package dev.lapinski.creditsuisse.csbackend.smoothie

import dev.lapinski.creditsuisse.csbackend.AppClock
import dev.lapinski.creditsuisse.csbackend.dto.DtoConverter
import dev.lapinski.creditsuisse.csbackend.controllers.SmoothieDetailsDto
import org.springframework.stereotype.Component

@Component
class SmoothieDetailsDtoConverter(
    private val nutritionalValueDtoConverter: NutritionalValueDtoConverter,
    private val clock: AppClock
) : DtoConverter<Smoothie, SmoothieDetailsDto> {
    override fun domainToDto(domain: Smoothie) = SmoothieDetailsDto(
        id = domain.id,
        addedAt = domain.addedAt,
        name = domain.name,
        description = domain.description,
        nutritionalValues = domain.nutritionalValues.map(nutritionalValueDtoConverter::domainToDto).toSet(),
        availableInStock = domain.availableInStock
    )

    override fun dtoToDomain(dto: SmoothieDetailsDto) = Smoothie(
        id = dto.id,
        addedAt = dto.addedAt ?: clock.localDateTimeNow(),
        name = dto.name,
        description = dto.description,
        nutritionalValues = dto.nutritionalValues.map(nutritionalValueDtoConverter::dtoToDomain).toSet(),
        availableInStock = dto.availableInStock
    )
}