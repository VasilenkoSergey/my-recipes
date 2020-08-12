package io.vasilenko.myrecipes.presentation.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.domain.usecase.LoadAllCategoriesUseCase
import io.vasilenko.myrecipes.domain.usecase.LoadAllRecipesUseCase
import io.vasilenko.myrecipes.presentation.catalog.adapter.CatalogGroupEmptyItem
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

    private val catalogItems = mutableListOf<ListItem>()

    val catalog: LiveData<List<ListItem>> get() = getCatalogData().asLiveData()

    private fun getCatalogData(): Flow<List<ListItem>> = combine(
        loadAllCategoriesUseCase.execute(),
        loadAllRecipesUseCase.execute()
    ) { categories, recipes ->
        addItemsToCatalog(categories, recipes)
    }

    private fun addItemsToCatalog(
        categories: List<CategoryEntity>,
        recipes: List<RecipeEntity>
    ): List<ListItem> {
        addCategories(categories)
        addRecipes(recipes)

        return if (catalogItems.isEmpty()) {
            listOf(CatalogGroupEmptyItem("Каталог пуст"))
        } else {
            catalogItems
        }
    }

    private fun addCategories(categories: List<CategoryEntity>) {
        val categoryItems = categoriesMapper.mapEntitiesToCatalogGroup(categories, "Категории")
        if (categoryItems.recipes.isNotEmpty()) {
            catalogItems.add(categoryItems)
        }
    }

    private fun addRecipes(recipes: List<RecipeEntity>) {
        val recipeItems = recipesMapper.mapEntitiesToCatalogGroup(recipes, "Рецепты")
        if (recipeItems.recipes.isNotEmpty()) {
            catalogItems.add(recipeItems)
        }
    }
}
