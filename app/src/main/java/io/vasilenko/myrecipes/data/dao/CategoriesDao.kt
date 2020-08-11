package io.vasilenko.myrecipes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.vasilenko.myrecipes.data.entity.Category

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM categories")
    suspend fun findAll(): List<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(category: Category)
}