package io.vasilenko.myrecipes.data.repo

import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.domain.repo.CategoriesRepo

class CategoriesRepository : CategoriesRepo {

    override suspend fun getAll(): List<CategoryEntity> {
        TODO("Not yet implemented")
    }
}