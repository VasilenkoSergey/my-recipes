package io.vasilenko.myrecipes.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface RecipesDao {

    @Query("SELECT * FROM recipes")
    suspend fun getAll(): List<Recipe>
}