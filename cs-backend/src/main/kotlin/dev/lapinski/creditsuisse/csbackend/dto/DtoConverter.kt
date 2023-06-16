package dev.lapinski.creditsuisse.csbackend.dto

interface DtoConverter<DomainClass : Any, DtoClass : Any> : DomainToDtoConverter<DomainClass, DtoClass> {
    fun dtoToDomain(dto: DtoClass): DomainClass
}