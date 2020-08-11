package io.vasilenko.myrecipes.data.dao

import androidx.room.*
import io.vasilenko.myrecipes.data.entity.Recipe
import io.vasilenko.myrecipes.data.entity.RecipeWithCategory

@Dao
interface RecipesDao {

    @Query("SELECT * FROM recipes")
    suspend fun findAll(): List<Recipe>

    @Transaction
    @Query("SELECT * FROM recipes")
    suspend fun findAllWithCategory(): List<RecipeWithCategory>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(recipe: Recipe)
}