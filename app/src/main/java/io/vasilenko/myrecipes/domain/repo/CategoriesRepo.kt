package io.vasilenko.myrecipes.domain.repo

import io.vasilenko.myrecipes.domain.entity.CategoryEntity

interface CategoriesRepo {

    suspend fun findAll(): List<CategoryEntity>

    suspend fun save(category: CategoryEntity)
}