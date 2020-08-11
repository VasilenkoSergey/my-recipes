package io.vasilenko.myrecipes.presentation.mapper

import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.presentation.model.CategoryModel

class CategoriesModelMapper {

    fun mapCategoryModelToEntity(category: CategoryModel): CategoryEntity {
        return CategoryEntity(
            id = category.id,
            name = category.title
        )
    }
}