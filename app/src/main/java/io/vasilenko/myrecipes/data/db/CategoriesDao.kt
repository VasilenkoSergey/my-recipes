package io.vasilenko.myrecipes.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM categories")
    suspend fun findAll(): List<Category>
}