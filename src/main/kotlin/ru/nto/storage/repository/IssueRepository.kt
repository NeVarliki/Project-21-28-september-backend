package ru.nto.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.nto.storage.entity.Equipment
import ru.nto.storage.entity.Issue

interface IssueRepository : JpaRepository<Issue, Long> {
    fun findByEquipmentAndReturnedAtIsNull(equipment: Equipment): Issue?
}
