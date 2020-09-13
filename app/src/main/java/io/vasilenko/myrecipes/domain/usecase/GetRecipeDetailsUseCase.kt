package io.vasilenko.myrecipes.domain.usecase

import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.domain.repo.RecipesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(
    private val repository: RecipesRepo
) : UseCase {

    fun execute(id: Long): Flow<RecipeEntity> {
        return repository.findById(id)
    }
}