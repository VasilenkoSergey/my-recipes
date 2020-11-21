package io.vasilenko.myrecipes.domain.repo

import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipesRepo {

    fun findAll(): Flow<List<RecipeEntity>>

    fun findById(id: Long): Flow<RecipeEntity>

    suspend fun save(recipe: RecipeEntity)
}