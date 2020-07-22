package io.vasilenko.myrecipes.data.mapper

import io.vasilenko.myrecipes.data.db.Category
import io.vasilenko.myrecipes.domain.entity.CategoryEntity

class CategoriesMapper {

    fun mapAll(categories: List<Category>): List<CategoryEntity> {
        return categories.map {
            CategoryEntity(
                id = it.id,
                name = it.name
            )
        }
    }
}