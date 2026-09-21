package ru.nto.storage.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import ru.nto.storage.entity.Equipment
import ru.nto.storage.entity.EquipmentStatus

interface EquipmentRepository : JpaRepository<Equipment, Long> {
    @EntityGraph(attributePaths = ["category"])
    fun findAllByStatus(status: EquipmentStatus): List<Equipment>
}
