package dev.lapinski.creditsuisse.csbackend.smoothie

import dev.lapinski.creditsuisse.csbackend.AppClock
import dev.lapinski.creditsuisse.csbackend.dto.DtoConverter
import dev.lapinski.creditsuisse.csbackend.controllers.NutritionalValueDto
import org.springframework.stereotype.Component

@Component
class NutritionalValueDtoConverter(private val clock: AppClock) : DtoConverter<NutritionalValue, NutritionalValueDto> {
    override fun domainToDto(domain: NutritionalValue) = NutritionalValueDto(
        id = domain.id,
        addedAt = domain.addedAt,
        name = domain.name,
        value = domain.value,
        unit = domain.unit
    )

    override fun dtoToDomain(dto: NutritionalValueDto) = NutritionalValue(
        id = dto.id,
        addedAt = dto.addedAt ?: clock.localDateTimeNow(),
        name = dto.name,
        value = dto.value,
        unit = dto.unit,
        smoothieId = null
    )

}