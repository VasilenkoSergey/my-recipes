package io.vasilenko.myrecipes.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vasilenko.myrecipes.presentation.common.ListItem
import io.vasilenko.myrecipes.presentation.model.BigRecipeItem
import io.vasilenko.myrecipes.presentation.model.Recipe
import io.vasilenko.myrecipes.presentation.model.RecipesGroupItem
import io.vasilenko.myrecipes.presentation.model.MediumRecipeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _data = MutableLiveData<List<ListItem>>()
    val data: LiveData<List<ListItem>> = _data

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val items = getItems()
            _data.postValue(items)
        }
    }

    private suspend fun getItems(): List<ListItem> {
        val favoriteRecipes = listOf<Recipe>(
            Recipe(1L, "Название", ""),
            Recipe(2L, "Название", ""),
            Recipe(3L, "Название", "")
        ).map {
            BigRecipeItem(
                id = it.id,
                title = it.title,
                image = it.image
            )
        }

        val latestRecipes = listOf<Recipe>(
            Recipe(1L, "Название", ""),
            Recipe(2L, "Название", ""),
            Recipe(3L, "Название", "")
        ).map {
            MediumRecipeItem(
                id = it.id,
                title = it.title,
                image = it.image
            )
        }

        val categories = listOf<Recipe>(
            Recipe(1L, "Название", ""),
            Recipe(2L, "Название", ""),
            Recipe(3L, "Название", "")
        ).map {
            MediumRecipeItem(
                id = it.id,
                title = it.title,
                image = it.image
            )
        }

        return listOf(
            RecipesGroupItem(
                "Избранное",
                favoriteRecipes
            ),
            RecipesGroupItem(
                "Все рецепты",
                latestRecipes
            ),
            RecipesGroupItem(
                "Категории",
                categories
            )
        )
    }
}
