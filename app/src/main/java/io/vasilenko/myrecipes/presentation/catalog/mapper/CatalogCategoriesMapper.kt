package io.vasilenko.myrecipes.presentation.catalog.mapper

import io.vasilenko.myrecipes.core.mapper.Mapper
import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogCategoriesGroupModel
import io.vasilenko.myrecipes.presentation.catalog.model.CatalogCategoryModel
import javax.inject.Inject

class CatalogCategoriesMapper @Inject constructor() : Mapper {

    fun mapEntitiesToModel(
        categories: List<CategoryEntity>,
        name: String
    ): CatalogCategoriesGroupModel {
        val items = categories.map {
            CatalogCategoryModel(
                id = it.id,
                title = it.name,
                image = ""
            )
        }
        return CatalogCategoriesGroupModel(
            name,
            items
        )
    }
}