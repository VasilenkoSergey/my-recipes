package io.vasilenko.myrecipes.data.repo

import io.vasilenko.myrecipes.data.dao.RecipesDao
import io.vasilenko.myrecipes.data.mapper.RecipesMapper
import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.domain.repo.RecipesRepo
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val dao: RecipesDao,
    private val recipesMapper: RecipesMapper
) : RecipesRepo {

    override suspend fun findAll(): List<RecipeEntity> {
        val recipes = dao.findAll()
        return recipesMapper.mapAll(recipes)
    }
}