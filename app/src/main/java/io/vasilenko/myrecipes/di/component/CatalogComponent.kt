package io.vasilenko.myrecipes.di.component

import dagger.BindsInstance
import dagger.Component
import io.vasilenko.myrecipes.core.FeatureScope
import io.vasilenko.myrecipes.core.ViewModelFactory
import io.vasilenko.myrecipes.core.resources.ResourceProvider
import io.vasilenko.myrecipes.data.dao.CategoriesDao
import io.vasilenko.myrecipes.data.dao.RecipesDao
import io.vasilenko.myrecipes.di.DI
import io.vasilenko.myrecipes.di.module.*

@FeatureScope
@Component(
    modules = [
        CatalogModule::class,
        CategoriesModule::class,
        CategoriesBindsModule::class,
        RecipesModule::class,
        RecipesBindsModule::class
    ]
)
interface CatalogComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun categoriesDao(dao: CategoriesDao): Builder

        @BindsInstance
        fun recipesDao(dao: RecipesDao): Builder

        @BindsInstance
        fun resources(resources: ResourceProvider): Builder

        fun build(): CatalogComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerCatalogComponent.builder()
                .categoriesDao(categoriesDao())
                .recipesDao(recipesDao())
                .resources(resources())
                .build()
        }
    }
}