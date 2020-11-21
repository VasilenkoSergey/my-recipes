package io.vasilenko.myrecipes.presentation.creation.recipe.mapper

import io.vasilenko.myrecipes.core.mapper.Mapper
import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.presentation.creation.recipe.model.RecipeCreationModel
import javax.inject.Inject

class RecipeCreationMapper @Inject constructor() : Mapper {

    fun mapRecipeModelToEntity(recipe: RecipeCreationModel): RecipeEntity {
        return RecipeEntity(
            id = recipe.id,
            name = recipe.title,
            categoryId = recipe.categoryId,
            image = recipe.image
        )
    }
}