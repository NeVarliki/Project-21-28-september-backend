package ru.nto.storage.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.nto.storage.controller.dto.EmployeeDto
import ru.nto.storage.service.EmployeeService

@RestController
@RequestMapping("/api")
class EmployeeController(
    private val employeeService: EmployeeService
) {

    @GetMapping("/{code}/auth")
    @ResponseStatus(HttpStatus.OK)
    fun login(@PathVariable code: String) {
        employeeService.auth(code)
    }

    @GetMapping("/{code}/info")
    @ResponseStatus(HttpStatus.OK)
    fun getByCode(@PathVariable code: String): EmployeeDto {
        return employeeService.getByCode(code)
    }
}
