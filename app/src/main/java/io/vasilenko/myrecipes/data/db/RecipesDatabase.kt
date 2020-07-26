package io.vasilenko.myrecipes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.vasilenko.myrecipes.data.dao.CategoriesDao
import io.vasilenko.myrecipes.data.dao.RecipesDao
import io.vasilenko.myrecipes.data.entity.Category
import io.vasilenko.myrecipes.data.entity.Recipe

@Database(entities = [Recipe::class, Category::class], version = 1, exportSchema = false)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

    abstract fun categoriesDao(): CategoriesDao
}