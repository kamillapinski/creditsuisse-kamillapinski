package dev.lapinski.creditsuisse.csbackend.smoothie.db

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "smoothies")
@SequenceGenerator(name = "seq_smoothies_id", allocationSize = 1)
data class DbSmoothie(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_smoothies_id")
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "added_at")
    var addedAt: LocalDateTime? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "available_in_stock")
    var availableInStock: Int? = null,

    @OneToMany(mappedBy = "smoothie", cascade = [CascadeType.ALL])
    var nutritionalValues: Collection<DbNutritionalValue> = ArrayList()
)
