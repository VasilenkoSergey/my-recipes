package io.vasilenko.myrecipes.data.repo

import io.vasilenko.myrecipes.data.dao.RecipesDao
import io.vasilenko.myrecipes.data.mapper.RecipesDataMapper
import io.vasilenko.myrecipes.domain.entity.RecipeEntity
import io.vasilenko.myrecipes.domain.repo.RecipesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val dao: RecipesDao,
    private val mapper: RecipesDataMapper
) : RecipesRepo {

    override fun findAll(): Flow<List<RecipeEntity>> {
        val recipes = dao.findAll()
        return mapper.mapRecipesToEntities(recipes)
    }

    override suspend fun save(recipe: RecipeEntity) {
        dao.save(mapper.mapEntityToRecipe(recipe))
    }
}