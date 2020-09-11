package io.vasilenko.myrecipes.di.component

import dagger.BindsInstance
import dagger.Component
import io.vasilenko.myrecipes.core.FeatureScope
import io.vasilenko.myrecipes.core.ViewModelFactory
import io.vasilenko.myrecipes.core.files.FileManager
import io.vasilenko.myrecipes.data.dao.CategoriesDao
import io.vasilenko.myrecipes.data.dao.RecipesDao
import io.vasilenko.myrecipes.di.DI
import io.vasilenko.myrecipes.di.module.CategoriesDataModule
import io.vasilenko.myrecipes.di.module.RecipeCreationModule
import io.vasilenko.myrecipes.di.module.RecipesDataModule

@FeatureScope
@Component(
    modules = [
        CategoriesDataModule::class,
        RecipesDataModule::class,
        RecipeCreationModule::class
    ]
)
interface RecipeCreationComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun recipesDao(dao: RecipesDao): Builder

        @BindsInstance
        fun categoriesDao(categoriesDao: CategoriesDao): Builder

        @BindsInstance
        fun fileManager(fileManager: FileManager): Builder

        fun build(): RecipeCreationComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerRecipeCreationComponent.builder()
                .recipesDao(recipesDao())
                .categoriesDao(categoriesDao())
                .fileManager(files())
                .build()
        }
    }
}