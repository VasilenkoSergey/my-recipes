package io.vasilenko.myrecipes.presentation.creation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vasilenko.myrecipes.domain.usecase.CreateCategoryUseCase
import io.vasilenko.myrecipes.presentation.creation.category.mapper.CategoryCreationMapper
import io.vasilenko.myrecipes.presentation.creation.category.model.CategoryCreationModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryCreationViewModel @Inject constructor(
    private val createUseCase: CreateCategoryUseCase,
    private val mapper: CategoryCreationMapper
) : ViewModel() {

    private val _isCreateButtonEnabled = MutableLiveData<Boolean>()
    val isCreateButtonEnabled: LiveData<Boolean> = _isCreateButtonEnabled

    private var name: String? = ""

    init {
        _isCreateButtonEnabled.value = false
    }

    fun afterNameTextChanged(text: String) {
        name = text
        checkData()
    }

    fun saveCategory(category: CategoryCreationModel) {
        viewModelScope.launch {
            createUseCase.createCategory(mapper.mapCategoryModelToEntity(category))
        }
    }

    private fun checkData() {
        _isCreateButtonEnabled.value = isNameValid()
    }

    private fun isNameValid(): Boolean? {
        return name?.isNotBlank()
    }
}