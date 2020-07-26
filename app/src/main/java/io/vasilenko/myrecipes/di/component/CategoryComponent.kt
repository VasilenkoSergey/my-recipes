package io.vasilenko.myrecipes.di.component

import dagger.BindsInstance
import dagger.Component
import io.vasilenko.myrecipes.core.FeatureScope
import io.vasilenko.myrecipes.core.ViewModelFactory
import io.vasilenko.myrecipes.data.dao.CategoriesDao
import io.vasilenko.myrecipes.di.DI
import io.vasilenko.myrecipes.di.module.CategoryBindsModule
import io.vasilenko.myrecipes.di.module.CategoryModule

@FeatureScope
@Component(
    modules = [
        CategoryModule::class,
        CategoryBindsModule::class
    ]
)
interface CategoryComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun categoriesDao(dao: CategoriesDao): Builder

        fun build(): CategoryComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerCategoryComponent.builder()
                .categoriesDao(categoriesDao())
                .build()
        }
    }
}