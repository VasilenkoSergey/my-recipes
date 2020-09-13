package io.vasilenko.myrecipes.di.component

import dagger.BindsInstance
import dagger.Component
import io.vasilenko.myrecipes.core.di.FeatureScope
import io.vasilenko.myrecipes.core.presentation.viewmodel.ViewModelFactory
import io.vasilenko.myrecipes.data.dao.RecipesDao
import io.vasilenko.myrecipes.di.DI
import io.vasilenko.myrecipes.di.module.RecipeDetailsModule
import io.vasilenko.myrecipes.di.module.RecipesDataModule

@FeatureScope
@Component(
    modules = [
        RecipesDataModule::class,
        RecipeDetailsModule::class
    ]
)
interface RecipeDetailsComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun recipesDao(dao: RecipesDao): Builder

        fun build(): RecipeDetailsComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerRecipeDetailsComponent.builder()
                .recipesDao(recipesDao())
                .build()
        }
    }
}