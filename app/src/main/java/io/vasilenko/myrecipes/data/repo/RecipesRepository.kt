package io.vasilenko.myrecipes.data.repo

import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.domain.repo.RecipesRepo

class RecipesRepository : RecipesRepo {

    override suspend fun getAll(): List<RecipeEntity> {
        TODO("Not yet implemented")
    }

}