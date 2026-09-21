package ru.nto.storage.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.nto.storage.controller.dto.EquipmentDto
import ru.nto.storage.controller.dto.IssueCreateDto
import ru.nto.storage.service.IssueService

@Validated
@RestController
@RequestMapping("/api")
class IssueController(
    private val issueService: IssueService
) {

    @GetMapping("/{code}/equipment")
    @ResponseStatus(HttpStatus.OK)
    fun getAvailable(@PathVariable code: String): Map<String, List<EquipmentDto>> {
        return issueService.getAvailableEquipment(code)
    }

    @PostMapping("/{code}/issue")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@PathVariable code: String, @RequestBody @Valid issueCreateDto: IssueCreateDto) {
        issueService.create(code, issueCreateDto)
    }
}
