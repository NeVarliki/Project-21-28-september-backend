package com.example.storage.service.impl;

import com.example.storage.controller.dto.EquipmentDto;
import com.example.storage.controller.dto.IssueCreateDto;
import com.example.storage.entity.Employee;
import com.example.storage.entity.Equipment;
import com.example.storage.entity.EquipmentStatus;
import com.example.storage.entity.Issue;
import com.example.storage.exception.EmployeeNotFoundException;
import com.example.storage.exception.EquipmentNotAvailableException;
import com.example.storage.exception.EquipmentNotFoundException;
import com.example.storage.repository.EmployeeRepository;
import com.example.storage.repository.EquipmentRepository;
import com.example.storage.repository.IssueRepository;
import com.example.storage.service.EmployeeService;
import com.example.storage.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final EmployeeRepository employeeRepository;
    private final EquipmentRepository equipmentRepository;
    private final EmployeeService employeeService;

    @Value("${issue.max-days}")
    private int maxDays;

    @Override
    @Transactional(readOnly = true)
    public Map<String, List<EquipmentDto>> getAvailableEquipment(String code) {
        employeeService.auth(code);

        Map<String, List<EquipmentDto>> result = new TreeMap<>();
        for (Equipment equipment : equipmentRepository.findAllByStatus(EquipmentStatus.AVAILABLE)) {
            String category = equipment.getCategory().getName();
            if (!result.containsKey(category)) {
                result.put(category, new java.util.ArrayList<>());
            }
            result.get(category).add(EquipmentDto.toDto(equipment));
        }

        return result;
    }

    @Override
    @Transactional
    public Issue create(String code, IssueCreateDto issueCreateDto) {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate returnDate = issueCreateDto.getReturnDate();
        if (returnDate.isBefore(today) || returnDate.isAfter(today.plusDays(maxDays))) {
            throw new IllegalArgumentException("Return date is out of range");
        }

        Employee employee = employeeRepository.findByCode(code)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with " + code + " code not found!"));

        long equipmentId = issueCreateDto.getEquipmentId();
        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment with " + equipmentId + " id not found!"));

        if (equipment.getStatus() != EquipmentStatus.AVAILABLE) {
            throw new EquipmentNotAvailableException("Equipment " + equipment.getInventoryCode() + " is not available");
        }

        if (issueRepository.findByEquipmentAndReturnedAtIsNull(equipment).isPresent()) {
            throw new EquipmentNotAvailableException("Equipment " + equipment.getInventoryCode() + " is already issued");
        }

        equipment.setStatus(EquipmentStatus.ISSUED);
        equipmentRepository.save(equipment);

        Issue issue = Issue.builder()
                .issueDate(today)
                .returnDate(returnDate)
                .employee(employee)
                .equipment(equipment)
                .build();

        return issueRepository.save(issue);
    }
}
