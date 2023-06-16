package dev.lapinski.creditsuisse.csbackend.controllers

import com.ninjasquad.springmockk.MockkBean
import dev.lapinski.creditsuisse.csbackend.smoothie.NutritionalValueUnit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import org.hamcrest.Matchers.startsWith
import org.hamcrest.core.Is.`is`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime

@WebMvcTest(SmoothieController::class)
class SmoothieControllerTest : DescribeSpec() {
    @MockkBean
    private lateinit var smoothieFacade: SmoothieFacade

    @Autowired
    private lateinit var mockMvc: MockMvc

    override fun extensions() = listOf(SpringExtension)

    val now = LocalDateTime.of(2000, 1, 1, 0, 0, 0)

    init {
        describe("listSmoothies") {
            it("returns correct smoothie") {
                val expectedDto = ListedSmoothieDto(
                    id = 1,
                    name = "2",
                    description = "3",
                    availableInStock = 4
                )

                every { smoothieFacade.listSmoothies() } returns listOf(expectedDto)

                mockMvc.get("/smoothie").andExpect {
                    status { is2xxSuccessful() }
                    jsonPath("$[0].id", `is`(expectedDto.id.toInt()))
                    jsonPath("$[0].name", `is`(expectedDto.name))
                    jsonPath("$[0].description", `is`(expectedDto.description))
                    jsonPath("$[0].availableInStock", `is`(expectedDto.availableInStock))
                }
            }
        }

        describe("getSmoothieById") {
            it("returns 404 when no smoothie found") {
                val smoothieId = 1444L
                every { smoothieFacade.getSmoothieById(smoothieId) } returns null

                mockMvc.get("/smoothie/$smoothieId").andExpect {
                    status { isNotFound() }
                }
            }

            it("returns full smoothie when found") {
                val smoothieId = 1L
                val nutritionalValueDto = NutritionalValueDto(
                    id = 2,
                    addedAt = now,
                    name = "6",
                    value = 7.toBigDecimal(),
                    unit = NutritionalValueUnit.G_PER_100G
                )
                val smoothieDto = SmoothieDetailsDto(
                    id = smoothieId,
                    addedAt = now,
                    name = "3",
                    description = "4",
                    nutritionalValues = setOf(
                        nutritionalValueDto
                    ),
                    availableInStock = 5
                )
                every { smoothieFacade.getSmoothieById(smoothieDto.id!!) } returns smoothieDto

                mockMvc.get("/smoothie/$smoothieId").andExpect {
                    status { is2xxSuccessful() }
                    jsonPath("$.id", `is`(smoothieId.toInt()))
                    jsonPath("$.addedAt", startsWith(smoothieDto.addedAt!!.toString()))
                    jsonPath("$.name", `is`(smoothieDto.name))
                    jsonPath("$.description", `is`(smoothieDto.description))
                    jsonPath("$.availableInStock", `is`(smoothieDto.availableInStock))

                    jsonPath("$.nutritionalValues[0].id", `is`(nutritionalValueDto.id!!.toInt()))
                    jsonPath("$.nutritionalValues[0].addedAt", startsWith(smoothieDto.addedAt!!.toString()))
                    jsonPath("$.nutritionalValues[0].name", `is`(nutritionalValueDto.name))
                    jsonPath("$.nutritionalValues[0].value", `is`(nutritionalValueDto.value.toInt()))
                    jsonPath("$.nutritionalValues[0].unit", `is`(nutritionalValueDto.unit.name))
                }
            }
        }

        describe("addSmoothie") { }

        describe("updateSmoothie") { }
    }
}
