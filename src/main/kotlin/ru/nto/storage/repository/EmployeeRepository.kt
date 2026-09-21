package ru.nto.storage.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import ru.nto.storage.entity.Employee

interface EmployeeRepository : JpaRepository<Employee, Long> {
    @EntityGraph(attributePaths = ["issueList", "issueList.equipment", "issueList.equipment.category"])
    fun findByCode(code: String): Employee?
}
