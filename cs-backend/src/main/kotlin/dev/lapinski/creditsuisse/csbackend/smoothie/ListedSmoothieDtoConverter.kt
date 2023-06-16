package dev.lapinski.creditsuisse.csbackend.smoothie

import dev.lapinski.creditsuisse.csbackend.controllers.ListedSmoothieDto
import dev.lapinski.creditsuisse.csbackend.dto.DomainToDtoConverter
import org.springframework.stereotype.Component

@Component
class ListedSmoothieDtoConverter : DomainToDtoConverter<ListedSmoothie, ListedSmoothieDto> {
    override fun domainToDto(domain: ListedSmoothie) = ListedSmoothieDto(
        id = domain.id,
        name = domain.name,
        description = domain.description,
        availableInStock = domain.availableInStock
    )
}