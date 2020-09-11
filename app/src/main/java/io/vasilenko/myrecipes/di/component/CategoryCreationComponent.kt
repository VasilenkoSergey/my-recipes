package io.vasilenko.myrecipes.di.component

import dagger.BindsInstance
import dagger.Component
import io.vasilenko.myrecipes.core.FeatureScope
import io.vasilenko.myrecipes.core.ViewModelFactory
import io.vasilenko.myrecipes.data.dao.CategoriesDao
import io.vasilenko.myrecipes.di.DI
import io.vasilenko.myrecipes.di.module.CategoriesDataModule
import io.vasilenko.myrecipes.di.module.CategoryCreationModule

@FeatureScope
@Component(
    modules = [
        CategoriesDataModule::class,
        CategoryCreationModule::class
    ]
)
interface CategoryCreationComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun categoriesDao(dao: CategoriesDao): Builder

        fun build(): CategoryCreationComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerCategoryCreationComponent.builder()
                .categoriesDao(categoriesDao())
                .build()
        }
    }
}