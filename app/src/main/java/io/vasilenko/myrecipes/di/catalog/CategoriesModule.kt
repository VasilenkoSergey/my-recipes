package io.vasilenko.myrecipes.di.catalog

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.vasilenko.myrecipes.core.ViewModelKey
import io.vasilenko.myrecipes.data.mapper.CategoriesMapper
import io.vasilenko.myrecipes.data.repo.CategoriesRepository
import io.vasilenko.myrecipes.domain.repo.CategoriesRepo
import io.vasilenko.myrecipes.domain.usecase.LoadAllCategoriesUseCase
import io.vasilenko.myrecipes.domain.usecase.UseCase
import io.vasilenko.myrecipes.presentation.catalog.CatalogViewModel

@Module
class CategoriesModule {

    @Provides
    fun provideCategoriesMapper(): CategoriesMapper {
        return CategoriesMapper()
    }
}

@Module
abstract class CategoriesBindsModule {

    @Binds
    abstract fun repository(repository: CategoriesRepository): CategoriesRepo

    @Binds
    abstract fun loadAllCategoriesUseCase(useCase: LoadAllCategoriesUseCase): UseCase

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    abstract fun bindCatalogViewModel(viewModel: CatalogViewModel): ViewModel
}