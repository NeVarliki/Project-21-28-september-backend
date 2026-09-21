package ru.nto.storage.controller.dto

import ru.nto.storage.entity.Employee

data class EmployeeDto(
    val name: String,
    val department: String?,
    val photoUrl: String?,
    val issues: List<IssueDto>
) {
    companion object {
        fun toDto(employee: Employee) = EmployeeDto(
            name = employee.name,
            department = employee.department,
            photoUrl = employee.photoUrl,
            issues = employee.issueList
                .filter { it.returnedAt == null }
                .map { IssueDto.toDto(it) }
        )
    }
}
