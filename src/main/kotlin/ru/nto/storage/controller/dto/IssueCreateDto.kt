package ru.nto.storage.controller.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.time.LocalDate

data class IssueCreateDto(
    @field:Positive
    val equipmentId: Long,
    @field:NotNull
    val returnDate: LocalDate
)
