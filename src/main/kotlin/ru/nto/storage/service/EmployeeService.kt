package ru.nto.storage.service

import ru.nto.storage.controller.dto.EmployeeDto

interface EmployeeService {
    fun getByCode(code: String): EmployeeDto

    fun auth(code: String)
}
