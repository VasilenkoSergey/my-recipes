package io.vasilenko.myrecipes.presentation.creation.recipe

import androidx.lifecycle.*
import io.vasilenko.myrecipes.domain.usecase.CreateRecipeUseCase
import io.vasilenko.myrecipes.domain.usecase.LoadAllCategoriesUseCase
import io.vasilenko.myrecipes.presentation.mapper.CategoriesModelMapper
import io.vasilenko.myrecipes.presentation.mapper.RecipesModelMapper
import io.vasilenko.myrecipes.presentation.model.CategoryModel
import io.vasilenko.myrecipes.presentation.model.RecipeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeCreationViewModel @Inject constructor(
    private val createUseCase: CreateRecipeUseCase,
    private val mapper: RecipesModelMapper,
    private val categoriesMapper: CategoriesModelMapper,
    private val loadAllCategoriesUseCase: LoadAllCategoriesUseCase
) : ViewModel() {

    private val _isCreateButtonEnabled = MutableLiveData<Boolean>()
    val isCreateButtonEnabled: LiveData<Boolean> = _isCreateButtonEnabled

    val categories: LiveData<List<CategoryModel>> = getCategoriesData().asLiveData()

    private var title: String? = ""

    init {
        _isCreateButtonEnabled.value = false
    }

    fun afterTitleTextChanged(text: String) {
        title = text
        checkData()
    }

    fun createRecipe(recipe: RecipeModel) {
        viewModelScope.launch {
            createUseCase.createRecipe(mapper.mapRecipeModelToEntity(recipe))
        }
    }

    private fun getCategoriesData(): Flow<List<CategoryModel>> = loadAllCategoriesUseCase.execute()
        .map {
            categoriesMapper.mapEntitiesToModels(it)
        }

    private fun checkData() {
        _isCreateButtonEnabled.value = isTitleValid()
    }

    private fun isTitleValid(): Boolean? {
        return title?.isNotBlank()
    }
}