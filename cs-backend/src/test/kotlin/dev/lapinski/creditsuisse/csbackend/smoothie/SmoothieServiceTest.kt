package dev.lapinski.creditsuisse.csbackend.smoothie

import dev.lapinski.creditsuisse.csbackend.PostgresContextInitializer
import dev.lapinski.creditsuisse.csbackend.smoothie.db.DbNutritionalValue
import dev.lapinski.creditsuisse.csbackend.smoothie.db.DbSmoothie
import dev.lapinski.creditsuisse.csbackend.smoothie.db.NutritionalValueRepository
import dev.lapinski.creditsuisse.csbackend.smoothie.db.SmoothieRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import java.time.LocalDateTime

@SpringBootTest
@ContextConfiguration(initializers = [PostgresContextInitializer::class])
class SmoothieServiceTest : DescribeSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var smoothieService: SmoothieService

    @Autowired
    lateinit var smoothieRepository: SmoothieRepository

    @Autowired
    lateinit var nutritionalValueRepository: NutritionalValueRepository

    init {
        describe("findSmoothieById") {
            val now = LocalDateTime.of(2000, 1, 1, 0, 0)
            var foundSmoothie: Smoothie? = null
            lateinit var dbSmoothie: DbSmoothie

            beforeTest {
                smoothieRepository.deleteAll()
                dbSmoothie = smoothieRepository.save(
                    DbSmoothie(
                        addedAt = now,
                        name = "NAME",
                        description = "DESCRIPTION",
                        availableInStock = 1,
                        nutritionalValues = emptySet()
                    )
                )

                nutritionalValueRepository.save(
                    DbNutritionalValue(
                        addedAt = now,
                        name = "X",
                        value = 1.0.toBigDecimal(),
                        unit = NutritionalValueUnit.G_PER_100G,
                        smoothie = dbSmoothie
                    )
                )

                foundSmoothie = smoothieService.findSmoothieById(dbSmoothie.id!!)
            }

            it("finds smoothie") {
                foundSmoothie shouldNotBe null
                foundSmoothie!!.id shouldBe dbSmoothie.id
            }

            it("has nutritional values") {
                val nutritionalValues = foundSmoothie!!.nutritionalValues

                nutritionalValues.size shouldBe 1
                val firstValue = nutritionalValues.first()

                firstValue.addedAt shouldBe now
                firstValue.name shouldBe "X"
            }
        }
    }
}
