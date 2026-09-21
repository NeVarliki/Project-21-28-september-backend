package ru.nto.storage.service

import ru.nto.storage.controller.dto.EquipmentDto
import ru.nto.storage.controller.dto.IssueCreateDto
import ru.nto.storage.entity.Issue

interface IssueService {
    fun getAvailableEquipment(code: String): Map<String, List<EquipmentDto>>

    fun create(code: String, issueCreateDto: IssueCreateDto): Issue
}
