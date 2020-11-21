package io.vasilenko.myrecipes.presentation.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vasilenko.myrecipes.core.presentation.viewmodel.SingleLiveEvent
import io.vasilenko.myrecipes.domain.usecase.GetRecipeDetailsUseCase
import io.vasilenko.myrecipes.presentation.recipedetails.mapper.RecipeDetailsMapper
import io.vasilenko.myrecipes.presentation.recipedetails.model.RecipeDetailsModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val mapper: RecipeDetailsMapper
) : ViewModel() {

    private val _recipeDetails = SingleLiveEvent<RecipeDetailsModel>()
    val recipeDetails: LiveData<RecipeDetailsModel> = _recipeDetails

    fun onViewCreated(recipeId: Long) {
        viewModelScope.launch {
            val flow = getRecipeDetailsUseCase.execute(recipeId)
            flow.collect { entity ->
                val recipe = mapper.mapEntityToModel(entity)
                _recipeDetails.postValue(recipe)
            }
        }
    }
}