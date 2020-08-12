package io.vasilenko.myrecipes.presentation.mapper

import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogCategoryModel
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogGroupModel
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

    fun mapEntitiesToCatalogGroup(
        categories: List<CategoryEntity>,
        name: String
    ): CatalogGroupModel {
        val items = categories.map {
            CatalogCategoryModel(
                id = it.id,
                title = it.name,
                image = ""
            )
        }
        return CatalogGroupModel(
            name,
            items
        )
    }
}