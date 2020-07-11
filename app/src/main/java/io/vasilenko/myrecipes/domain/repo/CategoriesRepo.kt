package io.vasilenko.myrecipes.domain.repo

import io.vasilenko.myrecipes.domain.entity.CategoryEntity

interface CategoriesRepo {

    suspend fun getAll(): List<CategoryEntity>
}