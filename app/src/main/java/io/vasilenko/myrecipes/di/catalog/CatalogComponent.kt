package io.vasilenko.myrecipes.di.catalog

import dagger.BindsInstance
import dagger.Component
import io.vasilenko.myrecipes.core.FeatureScope
import io.vasilenko.myrecipes.core.ViewModelFactory
import io.vasilenko.myrecipes.data.db.CategoriesDao
import io.vasilenko.myrecipes.di.DI

@FeatureScope
@Component(modules = [CategoriesModule::class, CategoriesBindsModule::class])
interface CatalogComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun dao(dao: CategoriesDao): Builder

        fun build(): CatalogComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerCatalogComponent.builder()
                .dao(categoriesDao())
                .build()
        }
    }
}