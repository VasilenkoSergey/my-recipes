package io.vasilenko.myrecipes.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.vasilenko.myrecipes.core.files.FileManager
import io.vasilenko.myrecipes.core.resources.ResourceProvider
import io.vasilenko.myrecipes.data.dao.CategoriesDao
import io.vasilenko.myrecipes.data.dao.RecipesDao
import io.vasilenko.myrecipes.data.db.RecipesDatabase
import io.vasilenko.myrecipes.di.module.DataBindsModule
import io.vasilenko.myrecipes.di.module.DataModule
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DataBindsModule::class])
interface AppComponent {

    fun database(): RecipesDatabase
    fun recipesDao(): RecipesDao
    fun categoriesDao(): CategoriesDao
    fun resources(): ResourceProvider
    fun files(): FileManager

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appContext(context: Context): Builder

        fun build(): AppComponent
    }
}