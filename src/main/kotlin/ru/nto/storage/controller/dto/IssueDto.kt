package ru.nto.storage.controller.dto

import ru.nto.storage.entity.Issue
import java.time.LocalDate

data class IssueDto(
    val id: Long,
    val equipment: EquipmentDto,
    val issueDate: LocalDate,
    val returnDate: LocalDate
) {
    companion object {
        fun toDto(issue: Issue) = IssueDto(
            id = issue.id,
            equipment = EquipmentDto.toDto(issue.equipment),
            issueDate = issue.issueDate,
            returnDate = issue.returnDate
        )
    }
}
