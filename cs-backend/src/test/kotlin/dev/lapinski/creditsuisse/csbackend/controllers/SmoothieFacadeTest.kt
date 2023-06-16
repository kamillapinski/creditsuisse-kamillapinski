package dev.lapinski.creditsuisse.csbackend.controllers

import dev.lapinski.creditsuisse.csbackend.smoothie.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime

class SmoothieFacadeTest : DescribeSpec({
    lateinit var smoothieFacade: SmoothieFacade

    lateinit var smoothieService: SmoothieService
    lateinit var smoothieDetailsDtoConverter: SmoothieDetailsDtoConverter
    lateinit var listedSmoothieDtoConverter: ListedSmoothieDtoConverter

    beforeEach {
        smoothieService = mockk()
        smoothieDetailsDtoConverter = mockk()
        listedSmoothieDtoConverter = mockk()

        smoothieFacade = SmoothieFacade(
            smoothieService,
            smoothieDetailsDtoConverter,
            listedSmoothieDtoConverter
        )
    }

    describe("listSmoothies") {
        lateinit var listedSmoothies: Collection<ListedSmoothieDto>

        val expectedDto = ListedSmoothieDto(
            id = 1,
            name = "2",
            description = "3",
            availableInStock = 4
        )
        beforeTest {
            val listedSmoothie = ListedSmoothie(
                id = 1,
                name = "2",
                description = "3",
                availableInStock = 4
            )
            every { smoothieService.listSmoothies() } returns setOf(listedSmoothie)
            every { listedSmoothieDtoConverter.domainToDto(listedSmoothie) } returns expectedDto

            listedSmoothies = smoothieFacade.listSmoothies()
        }

        it("returns valid smoothies number") {
            listedSmoothies.size shouldBe 1
        }

        it("gets smoothies from SmoothieService") {
            listedSmoothies.first() shouldBeEqual expectedDto
        }
    }

    describe("getSmoothieById") {
        val now = LocalDateTime.of(2000, 1, 1, 0, 0)
        val nullSmoothieId = 1331L
        val returnedSmoothie = Smoothie(
            id = 1,
            addedAt = now,
            name = "2",
            description = "3",
            nutritionalValues = emptySet(),
            availableInStock = 4
        )
        val returnedSmoothieDto = SmoothieDetailsDto(
            id = 1,
            addedAt = now,
            name = "2",
            description = "3",
            nutritionalValues = emptySet(),
            availableInStock = 4
        )

        beforeTest {
            every { smoothieService.findSmoothieById(returnedSmoothie.id!!) } returns returnedSmoothie
            every { smoothieService.findSmoothieById(nullSmoothieId) } returns null
            every { smoothieDetailsDtoConverter.domainToDto(returnedSmoothie) } returns returnedSmoothieDto
        }

        it("returns smoothie when found") {
            smoothieFacade.getSmoothieById(returnedSmoothie.id!!) shouldBe returnedSmoothieDto
        }

        it("returns null when smoothie not found") {
            smoothieFacade.getSmoothieById(nullSmoothieId) shouldBe null
        }
    }

    describe("addSmoothie") {
        //TODO
    }

    describe("updateSmoothie") {
        //TODO
    }
})
