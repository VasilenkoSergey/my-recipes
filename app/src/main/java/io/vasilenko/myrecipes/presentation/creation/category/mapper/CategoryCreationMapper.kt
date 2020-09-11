package io.vasilenko.myrecipes.presentation.creation.category.mapper

import io.vasilenko.myrecipes.core.mapper.Mapper
import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.presentation.creation.category.model.CategoryCreationModel
import javax.inject.Inject

class CategoryCreationMapper @Inject constructor() : Mapper {

    fun mapCategoryModelToEntity(category: CategoryCreationModel): CategoryEntity {
        return CategoryEntity(
            id = category.id,
            name = category.title
        )
    }

    fun mapEntitiesToModels(categories: List<CategoryEntity>): List<CategoryCreationModel> {
        return categories.map {
            CategoryCreationModel(
                id = it.id,
                title = it.name,
                image = ""
            )
        }
    }
}