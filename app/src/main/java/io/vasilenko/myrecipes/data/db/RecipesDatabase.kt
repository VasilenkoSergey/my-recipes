package io.vasilenko.myrecipes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class, Category::class], version = 1, exportSchema = false)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

    abstract fun categoriesDao(): CategoriesDao
}