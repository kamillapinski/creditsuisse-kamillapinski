package dev.lapinski.creditsuisse.csbackend.controllers

import dev.lapinski.creditsuisse.csbackend.smoothie.NutritionalValueUnit
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import java.math.BigDecimal
import java.time.LocalDateTime

@RestController
@RequestMapping("/smoothie")
class SmoothieController(private val smoothieFacade: SmoothieFacade) {
    @GetMapping("")
    fun listSmoothies(): Collection<ListedSmoothieDto> = smoothieFacade.listSmoothies()

    @GetMapping("/{id}")
    fun getSmoothieById(@PathVariable id: Long) =
        smoothieFacade.getSmoothieById(id) ?: throw HttpClientErrorException(HttpStatus.NOT_FOUND)

    @PostMapping("")
    fun addSmoothie(@RequestBody detailsDto: SmoothieDetailsDto) = smoothieFacade.addSmoothie(detailsDto)

    @PutMapping("")
    fun updateSmoothie(@RequestBody detailsDto: SmoothieDetailsDto) = smoothieFacade.updateSmoothie(detailsDto)
}

data class ListedSmoothieDto(
    val id: Long,
    val name: String,
    val description: String,
    val availableInStock: Int
)

data class SmoothieDetailsDto(
    val id: Long?,
    val addedAt: LocalDateTime?,
    val name: String,
    val description: String,
    val nutritionalValues: Set<NutritionalValueDto>,
    val availableInStock: Int
)

data class NutritionalValueDto(
    val id: Long?,
    val addedAt: LocalDateTime?,
    val name: String,
    val value: BigDecimal,
    val unit: NutritionalValueUnit
)