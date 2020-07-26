package io.vasilenko.myrecipes.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vasilenko.myrecipes.domain.usecase.CreateCategoryUseCase
import io.vasilenko.myrecipes.presentation.mapper.CategoriesModelMapper
import io.vasilenko.myrecipes.presentation.model.CategoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val createUseCase: CreateCategoryUseCase,
    private val mapper: CategoriesModelMapper
) : ViewModel() {

    fun saveCategory(category: CategoryModel) {
        viewModelScope.launch(Dispatchers.IO) {
            createUseCase.createCategory(mapper.mapCategoryModelToEntity(category))
        }
    }
}