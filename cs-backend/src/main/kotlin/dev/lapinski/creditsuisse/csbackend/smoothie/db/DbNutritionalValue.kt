package dev.lapinski.creditsuisse.csbackend.smoothie.db

import dev.lapinski.creditsuisse.csbackend.smoothie.NutritionalValueUnit
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "nutritional_values")
@SequenceGenerator(name = "seq_nutritional_values_id", allocationSize = 1)
data class DbNutritionalValue(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nutritional_values_id")
    var id: Long? = null,

    @Column(name = "added_at")
    var addedAt: LocalDateTime,

    @Column(name = "name")
    var name: String,

    @Column(name = "value")
    var value: BigDecimal,

    @Column(name = "unit")
    @Enumerated(EnumType.STRING)
    var unit: NutritionalValueUnit,

    @JoinColumn(name = "smoothie_id")
    @ManyToOne(cascade = [])
    var smoothie: DbSmoothie? = null,
)
