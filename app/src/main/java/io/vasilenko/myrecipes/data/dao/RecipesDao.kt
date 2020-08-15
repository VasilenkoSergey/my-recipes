package io.vasilenko.myrecipes.data.dao

import androidx.room.*
import io.vasilenko.myrecipes.data.entity.Recipe
import io.vasilenko.myrecipes.data.entity.RecipeWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Query("SELECT * FROM recipes")
    fun findAll(): Flow<List<Recipe>>

    @Transaction
    @Query("SELECT * FROM recipes")
    fun findAllWithCategory(): Flow<List<RecipeWithCategory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(recipe: Recipe)
}