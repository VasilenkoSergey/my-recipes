package io.vasilenko.myrecipes.presentation.creation.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vasilenko.myrecipes.domain.usecase.CreateRecipeUseCase
import io.vasilenko.myrecipes.domain.usecase.LoadAllCategoriesUseCase
import io.vasilenko.myrecipes.presentation.catalog.adapter.CatalogCategoryItem
import io.vasilenko.myrecipes.presentation.mapper.CategoriesModelMapper
import io.vasilenko.myrecipes.presentation.mapper.RecipesModelMapper
import io.vasilenko.myrecipes.presentation.model.CategoryModel
import io.vasilenko.myrecipes.presentation.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeCreationViewModel @Inject constructor(
    private val createUseCase: CreateRecipeUseCase,
    private val mapper: RecipesModelMapper,
    private val loadAllCategoriesUseCase: LoadAllCategoriesUseCase
) : ViewModel() {

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> = _categories

    init {
        viewModelScope.launch {
            _categories.postValue(
                loadAllCategoriesUseCase.execute().map {
                    CategoryModel(
                        id = it.id,
                        title = it.name,
                        image = ""
                    )
                }
            )
        }
    }

    fun createRecipe(recipe: RecipeModel) {
        viewModelScope.launch(Dispatchers.IO) {
            createUseCase.createRecipe(mapper.mapRecipeModelToEntity(recipe))
        }
    }
}