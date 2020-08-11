package io.vasilenko.myrecipes.di.component

import dagger.BindsInstance
import dagger.Component
import io.vasilenko.myrecipes.core.FeatureScope
import io.vasilenko.myrecipes.core.ViewModelFactory
import io.vasilenko.myrecipes.data.dao.CategoriesDao
import io.vasilenko.myrecipes.data.dao.RecipesDao
import io.vasilenko.myrecipes.di.DI
import io.vasilenko.myrecipes.di.module.RecipeBindsModule
import io.vasilenko.myrecipes.di.module.RecipeModule

@FeatureScope
@Component(
    modules = [
        RecipeModule::class,
        RecipeBindsModule::class
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

        fun build(): RecipeCreationComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerRecipeCreationComponent.builder()
                .recipesDao(recipesDao())
                .categoriesDao(categoriesDao())
                .build()
        }
    }
}