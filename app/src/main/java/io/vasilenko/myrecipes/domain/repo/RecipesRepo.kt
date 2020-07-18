package io.vasilenko.myrecipes.domain.repo

import io.vasilenko.myrecipes.domain.entity.RecipeEntity

interface RecipesRepo {

    suspend fun findAll(): List<RecipeEntity>
}