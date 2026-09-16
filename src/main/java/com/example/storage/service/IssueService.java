package com.example.storage.service;

import com.example.storage.controller.dto.EquipmentDto;
import com.example.storage.controller.dto.IssueCreateDto;
import com.example.storage.entity.Issue;

import java.util.List;
import java.util.Map;

public interface IssueService {
    Map<String, List<EquipmentDto>> getAvailableEquipment(String code);

    Issue create(String code, IssueCreateDto issueCreateDto);
}
