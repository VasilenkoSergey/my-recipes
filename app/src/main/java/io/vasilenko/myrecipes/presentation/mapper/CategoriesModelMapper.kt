package io.vasilenko.myrecipes.presentation.mapper

import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.presentation.catalog.adapter.CatalogCategoryItem
import io.vasilenko.myrecipes.presentation.model.CategoryModel

class CategoriesModelMapper {

    fun mapCategoryModelToEntity(category: CategoryModel): CategoryEntity {
        return CategoryEntity(
            id = category.id,
            name = category.title
        )
    }

    fun mapEntitiesToModels(categories: List<CategoryEntity>): List<CategoryModel> {
        return categories.map {
            CategoryModel(
                id = it.id,
                title = it.name,
                image = ""
            )
        }
    }

    fun mapEntitiesToListItems(categories: List<CategoryEntity>): List<CatalogCategoryItem> {
        return categories.map {
            CatalogCategoryItem(
                id = it.id,
                title = it.name,
                image = ""
            )
        }
    }
}