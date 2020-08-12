package io.vasilenko.myrecipes.presentation.mapper

import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.presentation.catalog.adapter.CatalogGroupItem
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

    fun mapEntitiesToCatalogGroup(
        recipes: List<RecipeEntity>,
        name: String
    ): CatalogGroupItem {
        val items = recipes.map {
            CatalogRecipeItem(
                id = it.id,
                title = it.name,
                image = ""
            )
        }
        return CatalogGroupItem(
            name,
            items
        )
    }
}