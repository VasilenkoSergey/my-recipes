package io.vasilenko.myrecipes.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.vasilenko.myrecipes.core.resources.ResourceProvider
import io.vasilenko.myrecipes.data.db.CategoriesDao
import io.vasilenko.myrecipes.data.db.RecipesDao
import io.vasilenko.myrecipes.data.db.RecipesDatabase
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DataBindsModule::class])
interface AppComponent {

    fun database(): RecipesDatabase
    fun recipesDao(): RecipesDao
    fun categoriesDao(): CategoriesDao
    fun resources(): ResourceProvider

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }
}