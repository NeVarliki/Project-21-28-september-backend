package com.example.storage.controller.dto;

import com.example.storage.entity.Employee;
import com.example.storage.entity.Issue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private String name;
    private String department;
    private String photoUrl;
    private List<IssueDto> issues;

    public static EmployeeDto toDto(Employee employee) {
        List<IssueDto> issues = new ArrayList<>();
        for (Issue issue : employee.getIssueList()) {
            if (issue.getReturnedAt() == null) {
                issues.add(IssueDto.toDto(issue));
            }
        }

        return new EmployeeDto(employee.getName(), employee.getDepartment(), employee.getPhotoUrl(), issues);
    }
}
