package io.vasilenko.myrecipes.presentation.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.domain.usecase.LoadAllCategoriesUseCase
import io.vasilenko.myrecipes.domain.usecase.LoadAllRecipesUseCase
import io.vasilenko.myrecipes.presentation.catalog.adapter.CatalogGroupItem
import io.vasilenko.myrecipes.presentation.common.ListItem
import io.vasilenko.myrecipes.presentation.mapper.CategoriesModelMapper
import io.vasilenko.myrecipes.presentation.mapper.RecipesModelMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val loadAllCategoriesUseCase: LoadAllCategoriesUseCase,
    private val categoriesMapper: CategoriesModelMapper,
    private val loadAllRecipesUseCase: LoadAllRecipesUseCase,
    private val recipesMapper: RecipesModelMapper
) : ViewModel() {

    val catalog: LiveData<List<ListItem>> get() = getCatalogData().asLiveData()

    private fun getCatalogData(): Flow<List<ListItem>> = combine(
        loadAllCategoriesUseCase.execute(),
        loadAllRecipesUseCase.execute()
    ) { categories, recipes ->
        mapItemsToCatalog(categories, recipes)
    }

    private fun mapItemsToCatalog(
        categories: List<CategoryEntity>,
        recipes: List<RecipeEntity>
    ): List<ListItem> {
        val catalog = mutableListOf<ListItem>()
        val categoryItems = mapCategoriesToItems(categories)
        val recipeItems = mapRecipesToItems(recipes)
        if (categoryItems.recipes.isNotEmpty()) catalog.add(categoryItems)
        if (recipeItems.recipes.isNotEmpty()) catalog.add(recipeItems)
        return catalog
    }

    private fun mapCategoriesToItems(categories: List<CategoryEntity>): CatalogGroupItem {
        val items = categoriesMapper.mapEntitiesToListItems(categories)
        return CatalogGroupItem(
            "Категории",
            items
        )
    }

    private fun mapRecipesToItems(recipes: List<RecipeEntity>): CatalogGroupItem {
        val items = recipesMapper.mapEntitiesToListItems(recipes)
        return CatalogGroupItem(
            "Рецепты",
            items
        )
    }
}
