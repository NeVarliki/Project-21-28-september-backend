package com.example.storage.controller;


import com.example.storage.controller.dto.EmployeeDto;
import com.example.storage.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{code}/auth")
    @ResponseStatus(code = HttpStatus.OK)
    public void login(@PathVariable String code) {
        employeeService.auth(code);
    }

    @GetMapping("/{code}/info")
    @ResponseStatus(code = HttpStatus.OK)
    public EmployeeDto getByCode(@PathVariable String code) {
        return employeeService.getByCode(code);
    }

}
