package io.vasilenko.myrecipes.domain.repo

import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoriesRepo {

    fun findAll(): Flow<List<CategoryEntity>>

    suspend fun save(category: CategoryEntity)
}