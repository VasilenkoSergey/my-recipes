package io.vasilenko.myrecipes.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.vasilenko.myrecipes.data.mapper.CategoriesDataMapper
import io.vasilenko.myrecipes.data.repo.CategoriesRepository
import io.vasilenko.myrecipes.domain.repo.CategoriesRepo
import io.vasilenko.myrecipes.domain.usecase.LoadAllCategoriesUseCase
import io.vasilenko.myrecipes.domain.usecase.UseCase

@Module
class CategoriesModule {

    @Provides
    fun provideCategoriesMapper(): CategoriesDataMapper {
        return CategoriesDataMapper()
    }
}

@Module
abstract class CategoriesBindsModule {

    @Binds
    abstract fun repository(repository: CategoriesRepository): CategoriesRepo

    @Binds
    abstract fun loadAllCategoriesUseCase(useCase: LoadAllCategoriesUseCase): UseCase
}