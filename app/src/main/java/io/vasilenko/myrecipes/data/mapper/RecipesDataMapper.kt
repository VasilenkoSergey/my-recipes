package io.vasilenko.myrecipes.data.mapper

import io.vasilenko.myrecipes.data.entity.Recipe
import io.vasilenko.myrecipes.domain.entity.RecipeEntity

class RecipesDataMapper {

    fun mapRecipesToEntities(recipes: List<Recipe>): List<RecipeEntity> {
        return recipes.map {
            RecipeEntity(
                id = it.id,
                name = it.title
            )
        }
    }

    fun mapEntityToRecipe(recipe: RecipeEntity): Recipe {
        return Recipe(title = recipe.name)
    }
}