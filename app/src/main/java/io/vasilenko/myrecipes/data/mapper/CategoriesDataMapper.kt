package io.vasilenko.myrecipes.data.mapper

import io.vasilenko.myrecipes.data.entity.Category
import io.vasilenko.myrecipes.domain.entity.CategoryEntity

class CategoriesDataMapper {

    fun mapCategoriesToEntities(categories: List<Category>): List<CategoryEntity> {
        return categories.map {
            CategoryEntity(
                id = it.id,
                name = it.name
            )
        }
    }

    fun mapEntityToCategory(category: CategoryEntity): Category {
        return Category(name = category.name)
    }
}