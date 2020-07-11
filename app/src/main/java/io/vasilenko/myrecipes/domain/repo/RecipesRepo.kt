package io.vasilenko.myrecipes.domain.repo

import io.vasilenko.myrecipes.domain.entity.RecipeEntity

interface RecipesRepo {

    suspend fun getAll(): List<RecipeEntity>
}