package io.vasilenko.myrecipes.presentation.mapper

import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.presentation.catalog.adapter.CatalogRecipeItem
import io.vasilenko.myrecipes.presentation.model.RecipeModel

class RecipesModelMapper {

    fun mapRecipeModelToEntity(recipe: RecipeModel): RecipeEntity {
        return RecipeEntity(
            id = recipe.id,
            name = recipe.title,
            categoryId = recipe.categoryId
        )
    }

    fun mapEntitiesToListItems(recipes: List<RecipeEntity>): List<CatalogRecipeItem> {
        return recipes.map {
            CatalogRecipeItem(
                id = it.id,
                title = it.name,
                image = ""
            )
        }
    }
}