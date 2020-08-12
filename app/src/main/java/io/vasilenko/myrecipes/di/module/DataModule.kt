package io.vasilenko.myrecipes.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.vasilenko.myrecipes.core.ViewModelFactory
import io.vasilenko.myrecipes.core.resources.AndroidResourceProvider
import io.vasilenko.myrecipes.core.resources.ResourceProvider
import io.vasilenko.myrecipes.data.dao.CategoriesDao
import io.vasilenko.myrecipes.data.dao.RecipesDao
import io.vasilenko.myrecipes.data.db.RecipesDatabase
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): RecipesDatabase {
        return Room.databaseBuilder(
            context,
            RecipesDatabase::class.java,
            "recipes_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRecipesDao(database: RecipesDatabase): RecipesDao {
        return database.recipesDao()
    }

    @Singleton
    @Provides
    fun provideCategoriesDao(database: RecipesDatabase): CategoriesDao {
        return database.categoriesDao()
    }
}

@Module
abstract class DataBindsModule {

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @Singleton
    abstract fun bindResourceProvider(provider: AndroidResourceProvider): ResourceProvider
}