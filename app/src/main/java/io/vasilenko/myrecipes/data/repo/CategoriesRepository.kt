package io.vasilenko.myrecipes.data.repo

import io.vasilenko.myrecipes.data.db.CategoriesDao
import io.vasilenko.myrecipes.data.mapper.CategoriesMapper
import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.domain.repo.CategoriesRepo
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val dao: CategoriesDao,
    private val mapper: CategoriesMapper
) : CategoriesRepo {

    override suspend fun findAll(): List<CategoryEntity> {
        val categories = dao.findAll()
        return mapper.mapAll(categories)
    }
}