package com.example.storage.controller;

import com.example.storage.controller.dto.EquipmentDto;
import com.example.storage.controller.dto.IssueCreateDto;
import com.example.storage.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping("/{code}/equipment")
    @ResponseStatus(code = HttpStatus.OK)
    public Map<String, List<EquipmentDto>> getAvailable(@PathVariable String code) {
        return issueService.getAvailableEquipment(code);
    }

    @PostMapping("/{code}/issue")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@PathVariable String code, @RequestBody IssueCreateDto issueCreateDto) {
        issueService.create(code, issueCreateDto);
    }

}
