package io.vasilenko.myrecipes.data.mapper

import io.vasilenko.myrecipes.data.entity.Recipe
import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipesDataMapper {

    fun mapRecipesToEntities(recipes: Flow<List<Recipe>>): Flow<List<RecipeEntity>> {
        return recipes.map { list ->
            list.map {
                RecipeEntity(
                    id = it.id,
                    name = it.title,
                    categoryId = it.categoryId
                )
            }
        }
    }

    fun mapEntityToRecipe(recipe: RecipeEntity): Recipe {
        return Recipe(title = recipe.name, categoryId = recipe.categoryId)
    }
}