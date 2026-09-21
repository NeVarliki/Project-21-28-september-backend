package ru.nto.storage.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.nto.storage.controller.dto.EquipmentDto
import ru.nto.storage.controller.dto.IssueCreateDto
import ru.nto.storage.entity.EquipmentStatus
import ru.nto.storage.entity.Issue
import ru.nto.storage.exception.EmployeeNotFoundException
import ru.nto.storage.exception.EquipmentNotAvailableException
import ru.nto.storage.exception.EquipmentNotFoundException
import ru.nto.storage.repository.EmployeeRepository
import ru.nto.storage.repository.EquipmentRepository
import ru.nto.storage.repository.IssueRepository
import ru.nto.storage.service.EmployeeService
import ru.nto.storage.service.IssueService
import java.time.LocalDate
import java.time.ZoneId
import java.util.TreeMap

@Service
class IssueServiceImpl(
    private val issueRepository: IssueRepository,
    private val employeeRepository: EmployeeRepository,
    private val equipmentRepository: EquipmentRepository,
    private val employeeService: EmployeeService
) : IssueService {

    @Value("\${issue.max-days}")
    private var maxDays: Long = 0

    @Transactional(readOnly = true)
    override fun getAvailableEquipment(code: String): Map<String, List<EquipmentDto>> {
        employeeService.auth(code)

        val result = TreeMap<String, MutableList<EquipmentDto>>()
        for (equipment in equipmentRepository.findAllByStatus(EquipmentStatus.AVAILABLE)) {
            val category = equipment.category.name
            if (!result.containsKey(category)) {
                result[category] = mutableListOf()
            }
            result[category]!!.add(EquipmentDto.toDto(equipment))
        }

        return result
    }

    @Transactional
    override fun create(code: String, issueCreateDto: IssueCreateDto): Issue {
        val today = LocalDate.now(ZoneId.systemDefault())
        val returnDate = issueCreateDto.returnDate
        if (returnDate.isBefore(today) || returnDate.isAfter(today.plusDays(maxDays))) {
            throw IllegalArgumentException("Return date is out of range")
        }

        val employee = employeeRepository.findByCode(code)
            ?: throw EmployeeNotFoundException("Employee with $code code not found!")

        val equipmentId = issueCreateDto.equipmentId
        val equipment = equipmentRepository.findById(equipmentId).orElseThrow {
            EquipmentNotFoundException("Equipment with $equipmentId id not found!")
        }

        if (equipment.status != EquipmentStatus.AVAILABLE) {
            throw EquipmentNotAvailableException("Equipment ${equipment.inventoryCode} is not available")
        }

        if (issueRepository.findByEquipmentAndReturnedAtIsNull(equipment) != null) {
            throw EquipmentNotAvailableException("Equipment ${equipment.inventoryCode} is already issued")
        }

        equipment.status = EquipmentStatus.ISSUED
        equipmentRepository.save(equipment)

        val issue = Issue(
            issueDate = today,
            returnDate = returnDate,
            employee = employee,
            equipment = equipment
        )

        return issueRepository.save(issue)
    }
}
