package com.example.storage.controller.dto;

import com.example.storage.entity.Equipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto {
    private long id;
    private String name;
    private String inventoryCode;
    private String category;

    public static EquipmentDto toDto(Equipment equipment) {
        return new EquipmentDto(
                equipment.getId(),
                equipment.getName(),
                equipment.getInventoryCode(),
                equipment.getCategory().getName()
        );
    }
}
