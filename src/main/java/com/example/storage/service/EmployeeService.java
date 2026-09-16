package com.example.storage.service;

import com.example.storage.controller.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto getByCode(String code);

    void auth(String code);
}
