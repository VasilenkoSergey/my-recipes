package io.vasilenko.myrecipes.presentation.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vasilenko.myrecipes.domain.usecase.LoadAllCategoriesUseCase
import io.vasilenko.myrecipes.domain.usecase.LoadAllRecipesUseCase
import io.vasilenko.myrecipes.presentation.common.ListItem
import io.vasilenko.myrecipes.presentation.model.CatalogCategoryItem
import io.vasilenko.myrecipes.presentation.model.CatalogEmptyItem
import io.vasilenko.myrecipes.presentation.model.CatalogGroupItem
import io.vasilenko.myrecipes.presentation.model.CatalogRecipeItem
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

        val emptyFavorites = CatalogEmptyItem("Список избранного пуст")
        val favoriteRecipes = emptyList<ListItem>()
        val favoriteItems: List<ListItem>
        favoriteItems =
            if (favoriteRecipes.isNotEmpty()) favoriteRecipes else listOf(emptyFavorites)

        val emptyRecipes = CatalogEmptyItem("Рецепты отсутствуют")
        val recipes = loadAllRecipesUseCase.execute()
            .map {
                CatalogRecipeItem(
                    id = it.id,
                    title = it.name,
                    image = ""
                )
            }
        val recipeItems: List<ListItem>
        recipeItems = if (recipes.isNotEmpty()) recipes else listOf(emptyRecipes)

        val emptyCategories = CatalogEmptyItem("Категории не добавлены")
        val categories = loadAllCategoriesUseCase.execute()
            .map {
                CatalogCategoryItem(
                    id = it.id,
                    title = it.name,
                    image = ""
                )
            }
        val categoryItems: List<ListItem>
        categoryItems = if (categories.isNotEmpty()) categories else listOf(emptyCategories)

        return listOf(
            CatalogGroupItem(
                "Избранное",
                favoriteItems
            ),
            CatalogGroupItem(
                "Рецепты",
                recipeItems
            ),
            CatalogGroupItem(
                "Категории",
                categoryItems
            )
        )
    }
}
