package io.vasilenko.myrecipes.data.mapper

import io.vasilenko.myrecipes.core.Mapper
import io.vasilenko.myrecipes.data.entity.Category
import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoriesDataMapper @Inject constructor() : Mapper {

    fun mapCategoriesToEntities(categories: Flow<List<Category>>): Flow<List<CategoryEntity>> {
        return categories.map { list ->
            list.map {
                CategoryEntity(
                    id = it.id,
                    name = it.name
                )
            }
        }
    }

    fun mapEntityToCategory(category: CategoryEntity): Category {
        return Category(name = category.name)
    }
}