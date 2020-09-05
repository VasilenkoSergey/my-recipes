package io.vasilenko.myrecipes.presentation.mapper

import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogRecipeModel
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogRecipesGroupModel
import io.vasilenko.myrecipes.presentation.model.RecipeModel

class RecipesModelMapper {

    fun mapRecipeModelToEntity(recipe: RecipeModel): RecipeEntity {
        return RecipeEntity(
            id = recipe.id,
            name = recipe.title,
            categoryId = recipe.categoryId,
            image = recipe.image
        )
    }

    fun mapEntitiesToCatalogGroup(
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