package io.vasilenko.myrecipes.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.vasilenko.myrecipes.core.ViewModelKey
import io.vasilenko.myrecipes.data.mapper.CategoriesDataMapper
import io.vasilenko.myrecipes.data.repo.CategoriesRepository
import io.vasilenko.myrecipes.domain.repo.CategoriesRepo
import io.vasilenko.myrecipes.domain.usecase.CreateCategoryUseCase
import io.vasilenko.myrecipes.domain.usecase.UseCase
import io.vasilenko.myrecipes.presentation.creation.category.CategoryCreationViewModel
import io.vasilenko.myrecipes.presentation.mapper.CategoriesModelMapper

@Module
class CategoryModule {

    @Provides
    fun provideCategoriesMapper(): CategoriesDataMapper {
        return CategoriesDataMapper()
    }

    @Provides
    fun provideCategoryMapper(): CategoriesModelMapper {
        return CategoriesModelMapper()
    }
}

@Module
abstract class CategoryBindsModule {

    @Binds
    abstract fun repository(repository: CategoriesRepository): CategoriesRepo

    @Binds
    abstract fun createCategoryUseCase(useCase: CreateCategoryUseCase): UseCase

    @Binds
    @IntoMap
    @ViewModelKey(CategoryCreationViewModel::class)
    abstract fun bindCatalogViewModel(viewModel: CategoryCreationViewModel): ViewModel
}