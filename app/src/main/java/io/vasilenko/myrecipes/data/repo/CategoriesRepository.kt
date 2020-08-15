package io.vasilenko.myrecipes.data.repo

import io.vasilenko.myrecipes.data.dao.CategoriesDao
import io.vasilenko.myrecipes.data.mapper.CategoriesDataMapper
import io.vasilenko.myrecipes.domain.entity.CategoryEntity
import io.vasilenko.myrecipes.domain.repo.CategoriesRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val dao: CategoriesDao,
    private val mapper: CategoriesDataMapper
) : CategoriesRepo {

    override fun findAll(): Flow<List<CategoryEntity>> {
        val categories = dao.findAll()
        return mapper.mapCategoriesToEntities(categories)
    }

    override suspend fun save(category: CategoryEntity) {
        dao.save(mapper.mapEntityToCategory(category))
    }
}