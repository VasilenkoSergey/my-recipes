package io.vasilenko.myrecipes.presentation.creation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vasilenko.myrecipes.domain.usecase.CreateRecipeUseCase
import io.vasilenko.myrecipes.presentation.mapper.RecipesModelMapper
import io.vasilenko.myrecipes.presentation.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeCreationViewModel @Inject constructor(
    private val createUseCase: CreateRecipeUseCase,
    private val mapper: RecipesModelMapper
) : ViewModel() {

    fun createRecipe(recipe: RecipeModel) {
        viewModelScope.launch(Dispatchers.IO) {
            createUseCase.createRecipe(mapper.mapRecipeModelToEntity(recipe))
        }
    }
}