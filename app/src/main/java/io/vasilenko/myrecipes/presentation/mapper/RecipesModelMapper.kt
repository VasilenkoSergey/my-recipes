package io.vasilenko.myrecipes.presentation.mapper

import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.presentation.model.RecipeModel

class RecipesModelMapper {

    fun mapRecipeModelToEntity(recipe: RecipeModel): RecipeEntity {
        return RecipeEntity(
            id = recipe.id,
            name = recipe.title
        )
    }
}