package dev.lapinski.creditsuisse.csbackend.smoothie.db

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SmoothieRepository : CrudRepository<DbSmoothie, Long> {
    @Query("from DbSmoothie order by addedAt desc")
    fun findAllOrderByAddedAtDesc(): List<DbSmoothie>

    @Query("from DbSmoothie dbs join fetch dbs.nutritionalValues where dbs.id = :id")
    fun findFullById(id: Long): DbSmoothie?
}