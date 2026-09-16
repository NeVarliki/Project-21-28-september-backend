package com.example.storage.repository;

import com.example.storage.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @EntityGraph(attributePaths = {"issueList", "issueList.equipment", "issueList.equipment.category"})
    Optional<Employee> findByCode(String code);
}
