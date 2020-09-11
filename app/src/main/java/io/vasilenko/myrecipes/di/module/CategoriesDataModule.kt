package io.vasilenko.myrecipes.di.module

import dagger.Binds
import dagger.Module
import io.vasilenko.myrecipes.data.repo.CategoriesRepository
import io.vasilenko.myrecipes.domain.repo.CategoriesRepo

@Module
abstract class CategoriesDataModule {

    @Binds
    abstract fun bindCategoriesRepository(repository: CategoriesRepository): CategoriesRepo
}