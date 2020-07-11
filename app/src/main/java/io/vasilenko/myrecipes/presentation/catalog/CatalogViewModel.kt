package io.vasilenko.myrecipes.presentation.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vasilenko.myrecipes.presentation.common.ListItem
import io.vasilenko.myrecipes.presentation.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {

    private val _data = MutableLiveData<List<ListItem>>()
    val data: LiveData<List<ListItem>> = _data

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val items = getItems()
            _data.postValue(items)
        }
    }

    private fun getItems(): List<ListItem> {
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

        val latestRecipes = listOf(
            Recipe(1L, "Название", ""),
            Recipe(2L, "Название", ""),
            Recipe(3L, "Название", "")
        ).map {
            CatalogRecipeItem(
                id = it.id,
                title = it.title,
                image = it.image
            )
        }

        val categories = listOf(
            Category(1L, "Название", ""),
            Category(2L, "Название", ""),
            Category(3L, "Название", "")
        ).map {
            CatalogCategoryItem(
                id = it.id,
                title = it.title,
                image = it.image
            )
        }

        return listOf(
            CatalogGroupItem(
                "Избранное",
                favoriteRecipes
            ),
            CatalogGroupItem(
                "Рецепты",
                latestRecipes
            ),
            CatalogGroupItem(
                "Категории",
                categories
            )
        )
    }
}
