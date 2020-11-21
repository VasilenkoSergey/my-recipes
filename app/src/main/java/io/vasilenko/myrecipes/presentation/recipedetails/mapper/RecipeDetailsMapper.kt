package io.vasilenko.myrecipes.presentation.recipedetails.mapper

import io.vasilenko.myrecipes.core.mapper.Mapper
import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.presentation.recipedetails.model.RecipeDetailsModel
import javax.inject.Inject

class RecipeDetailsMapper @Inject constructor() : Mapper {

    fun mapEntityToModel(entity: RecipeEntity): RecipeDetailsModel {
        return RecipeDetailsModel(
            id = entity.id,
            title = entity.name,
            image = entity.image
        )
    }
}