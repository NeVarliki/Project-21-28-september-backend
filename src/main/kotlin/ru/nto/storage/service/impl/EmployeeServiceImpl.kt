package ru.nto.storage.service.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.nto.storage.controller.dto.EmployeeDto
import ru.nto.storage.exception.EmployeeNotFoundException
import ru.nto.storage.repository.EmployeeRepository
import ru.nto.storage.service.EmployeeService

@Service
class EmployeeServiceImpl(
    private val employeeRepository: EmployeeRepository
) : EmployeeService {

    @Transactional(readOnly = true)
    override fun getByCode(code: String): EmployeeDto {
        val employee = employeeRepository.findByCode(code)
            ?: throw EmployeeNotFoundException("Employee with $code code not found!")
        return EmployeeDto.toDto(employee)
    }

    @Transactional(readOnly = true)
    override fun auth(code: String) {
        if (employeeRepository.findByCode(code) == null) {
            throw EmployeeNotFoundException("Employee with $code code not found!")
        }
    }
}
