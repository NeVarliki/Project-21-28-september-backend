package com.example.storage.service.impl;

import com.example.storage.controller.dto.EmployeeDto;
import com.example.storage.exception.EmployeeNotFoundException;
import com.example.storage.repository.EmployeeRepository;
import com.example.storage.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto getByCode(String code) {
        return employeeRepository.findByCode(code).map(EmployeeDto::toDto)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with " + code + " code not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public void auth(String code) {
        if (employeeRepository.findByCode(code).isEmpty()) {
            throw new EmployeeNotFoundException("Employee with " + code + " code not found!");
        }
    }
}
