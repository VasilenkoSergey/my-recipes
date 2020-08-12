package io.vasilenko.myrecipes.presentation.mapper

import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogGroupModel
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogRecipeModel
import io.vasilenko.myrecipes.presentation.model.RecipeModel

class RecipesModelMapper {

    fun mapRecipeModelToEntity(recipe: RecipeModel): RecipeEntity {
        return RecipeEntity(
            id = recipe.id,
            name = recipe.title,
            categoryId = recipe.categoryId
        )
    }

    fun mapEntitiesToCatalogGroup(
        recipes: List<RecipeEntity>,
        name: String
    ): CatalogGroupModel {
        val items = recipes.map {
            CatalogRecipeModel(
                id = it.id,
                title = it.name,
                image = ""
            )
        }
        return CatalogGroupModel(
            name,
            items
        )
    }
}