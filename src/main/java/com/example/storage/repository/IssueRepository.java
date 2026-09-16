package com.example.storage.repository;

import com.example.storage.entity.Equipment;
import com.example.storage.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    Optional<Issue> findByEquipmentAndReturnedAtIsNull(Equipment equipment);
}
