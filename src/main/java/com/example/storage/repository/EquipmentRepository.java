package com.example.storage.repository;

import com.example.storage.entity.Equipment;
import com.example.storage.entity.EquipmentStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @EntityGraph(attributePaths = {"category"})
    List<Equipment> findAllByStatus(EquipmentStatus status);
}
