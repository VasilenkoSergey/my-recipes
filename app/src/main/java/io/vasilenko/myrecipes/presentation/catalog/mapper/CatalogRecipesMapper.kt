package io.vasilenko.myrecipes.presentation.catalog.mapper

import io.vasilenko.myrecipes.core.Mapper
import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogRecipeModel
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogRecipesGroupModel
import javax.inject.Inject

class CatalogRecipesMapper @Inject constructor() : Mapper {

    fun mapEntitiesToModel(
        recipes: List<RecipeEntity>,
        name: String
    ): CatalogRecipesGroupModel {
        val items = recipes.map {
            CatalogRecipeModel(
                id = it.id,
                title = it.name,
                image = it.image
            )
        }
        return CatalogRecipesGroupModel(
            name,
            items
        )
    }
}