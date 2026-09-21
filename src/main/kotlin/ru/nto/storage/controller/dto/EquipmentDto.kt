package ru.nto.storage.controller.dto

import ru.nto.storage.entity.Equipment

data class EquipmentDto(
    val id: Long,
    val name: String,
    val inventoryCode: String,
    val category: String
) {
    companion object {
        fun toDto(equipment: Equipment) = EquipmentDto(
            id = equipment.id,
            name = equipment.name,
            inventoryCode = equipment.inventoryCode,
            category = equipment.category.name
        )
    }
}
