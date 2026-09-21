package ru.nto.storage.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.nto.storage.entity.Category

interface CategoryRepository : JpaRepository<Category, Long>
