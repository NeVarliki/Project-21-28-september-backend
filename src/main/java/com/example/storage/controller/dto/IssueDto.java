package com.example.storage.controller.dto;

import com.example.storage.entity.Issue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueDto {
    private long id;
    private EquipmentDto equipment;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public static IssueDto toDto(Issue issue) {
        return new IssueDto(
                issue.getId(),
                EquipmentDto.toDto(issue.getEquipment()),
                issue.getIssueDate(),
                issue.getReturnDate()
        );
    }
}
