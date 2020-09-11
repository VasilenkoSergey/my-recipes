package io.vasilenko.myrecipes.presentation.creation.recipe

import androidx.lifecycle.*
import io.vasilenko.myrecipes.domain.usecase.CreateRecipeUseCase
import io.vasilenko.myrecipes.domain.usecase.DeleteImageUseCase
import io.vasilenko.myrecipes.domain.usecase.LoadAllCategoriesUseCase
import io.vasilenko.myrecipes.presentation.creation.category.mapper.CategoryCreationMapper
import io.vasilenko.myrecipes.presentation.creation.category.model.CategoryCreationModel
import io.vasilenko.myrecipes.presentation.creation.recipe.mapper.RecipeCreationMapper
import io.vasilenko.myrecipes.presentation.creation.recipe.model.RecipeCreationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeCreationViewModel @Inject constructor(
    private val createUseCase: CreateRecipeUseCase,
    private val mapper: RecipeCreationMapper,
    private val categoriesMapper: CategoryCreationMapper,
    private val loadAllCategoriesUseCase: LoadAllCategoriesUseCase,
    private val deleteImageUseCase: DeleteImageUseCase
) : ViewModel() {

    private val _isCreateButtonEnabled = MutableLiveData<Boolean>()
    val isCreateButtonEnabled: LiveData<Boolean> = _isCreateButtonEnabled

    val categories: LiveData<List<CategoryCreationModel>> = getCategoriesData().asLiveData()

    private var title: String? = ""

    init {
        _isCreateButtonEnabled.value = false
    }

    fun afterTitleTextChanged(text: String) {
        title = text
        checkData()
    }

    fun createRecipe(recipe: RecipeCreationModel) {
        viewModelScope.launch {
            createUseCase.createRecipe(mapper.mapRecipeModelToEntity(recipe))
        }
    }

    fun onCancel(path: String) {
        if (path.isNotEmpty()) {
            deleteImageUseCase.perform(path)
        }
    }

    private fun getCategoriesData(): Flow<List<CategoryCreationModel>> =
        loadAllCategoriesUseCase.execute()
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