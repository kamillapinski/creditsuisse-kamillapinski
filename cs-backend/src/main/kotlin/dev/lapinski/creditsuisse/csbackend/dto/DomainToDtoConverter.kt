package dev.lapinski.creditsuisse.csbackend.dto

interface DomainToDtoConverter<DomainClass : Any, DtoClass : Any> {
    fun domainToDto(domain: DomainClass): DtoClass
}