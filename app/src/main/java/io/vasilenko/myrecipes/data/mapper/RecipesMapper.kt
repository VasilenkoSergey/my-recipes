package io.vasilenko.myrecipes.data.mapper

import io.vasilenko.myrecipes.data.entity.Recipe
import io.vasilenko.myrecipes.domain.entity.RecipeEntity

class RecipesMapper {

    fun mapAll(recipes: List<Recipe>): List<RecipeEntity> {
        return recipes.map {
            RecipeEntity(
                id = it.id,
                name = it.title
            )
        }
    }
}