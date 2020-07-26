package io.vasilenko.myrecipes.data.dao

import androidx.room.Dao
import androidx.room.Query
import io.vasilenko.myrecipes.data.entity.Recipe

@Dao
interface RecipesDao {

    @Query("SELECT * FROM recipes")
    suspend fun findAll(): List<Recipe>
}