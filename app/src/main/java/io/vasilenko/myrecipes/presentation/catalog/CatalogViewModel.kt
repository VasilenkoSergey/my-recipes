package io.vasilenko.myrecipes.presentation.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.vasilenko.myrecipes.R
import io.vasilenko.myrecipes.core.resources.ResourceProvider
import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.domain.usecase.LoadAllCategoriesUseCase
import io.vasilenko.myrecipes.domain.usecase.LoadAllRecipesUseCase
import io.vasilenko.myrecipes.presentation.catalog.mapper.CatalogCategoriesMapper
import io.vasilenko.myrecipes.presentation.catalog.mapper.CatalogRecipesMapper
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogEmptyGroupModel
import io.vasilenko.myrecipes.core.presentation.adapter.ListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val loadAllCategoriesUseCase: LoadAllCategoriesUseCase,
    private val categoriesMapper: CatalogCategoriesMapper,
    private val loadAllRecipesUseCase: LoadAllRecipesUseCase,
    private val recipesMapper: CatalogRecipesMapper,
    private val resources: ResourceProvider
) : ViewModel() {

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
        val catalogItems = mutableListOf<ListItem>()

        addCategories(categories, catalogItems)
        addRecipes(recipes, catalogItems)

        return if (catalogItems.isEmpty()) {
            listOf(
                CatalogEmptyGroupModel(resources.string(R.string.empty_catalog))
            )
        } else {
            catalogItems
        }
    }

    private fun addCategories(
        categories: List<CategoryEntity>,
        catalogItems: MutableList<ListItem>
    ) {
        val categoryItems = categoriesMapper.mapEntitiesToModel(
            categories,
            resources.string(R.string.categories)
        )
        if (categoryItems.recipes.isNotEmpty()) {
            catalogItems.add(categoryItems)
        }
    }

    private fun addRecipes(
        recipes: List<RecipeEntity>,
        catalogItems: MutableList<ListItem>
    ) {
        val recipeItems =
            recipesMapper.mapEntitiesToModel(recipes, resources.string(R.string.recipes))
        if (recipeItems.recipes.isNotEmpty()) {
            catalogItems.add(recipeItems)
        }
    }
}
