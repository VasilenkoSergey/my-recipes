package io.vasilenko.myrecipes.presentation.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vasilenko.myrecipes.domain.usecase.LoadAllCategoriesUseCase
import io.vasilenko.myrecipes.domain.usecase.LoadAllRecipesUseCase
import io.vasilenko.myrecipes.presentation.common.ListItem
import io.vasilenko.myrecipes.presentation.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val loadAllCategoriesUseCase: LoadAllCategoriesUseCase,
    private val loadAllRecipesUseCase: LoadAllRecipesUseCase
) : ViewModel() {

    private val _data = MutableLiveData<List<ListItem>>()
    val data: LiveData<List<ListItem>> = _data

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val items = getItems()
            _data.postValue(items)
        }
    }

    private suspend fun getItems(): List<ListItem> {
        val favoriteRecipes = listOf(
            Recipe(1L, "Название", ""),
            Recipe(2L, "Название", ""),
            Recipe(3L, "Название", "")
        ).map {
            CatalogFavoriteRecipeItem(
                id = it.id,
                title = it.title,
                image = it.image
            )
        }

        val recipes = loadAllRecipesUseCase.execute()
            .map {
                CatalogRecipeItem(
                    id = it.id,
                    title = it.name,
                    image = ""
                )
            }

        val categories = loadAllCategoriesUseCase.execute()
            .map {
                CatalogCategoryItem(
                    id = it.id,
                    title = it.name,
                    image = ""
                )
            }

        return listOf(
            CatalogGroupItem(
                "Избранное",
                favoriteRecipes
            ),
            CatalogGroupItem(
                "Рецепты",
                recipes
            ),
            CatalogGroupItem(
                "Категории",
                categories
            )
        )
    }
}
