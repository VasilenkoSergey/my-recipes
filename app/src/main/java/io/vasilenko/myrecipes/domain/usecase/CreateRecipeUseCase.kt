package io.vasilenko.myrecipes.domain.usecase

import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.domain.repo.RecipesRepo
import javax.inject.Inject

class CreateRecipeUseCase @Inject constructor(private val repository: RecipesRepo) : UseCase {

    suspend fun createRecipe(recipe: RecipeEntity) {
        repository.save(recipe)
    }
}