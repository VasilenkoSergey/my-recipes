package io.vasilenko.myrecipes.di.module

import dagger.Binds
import dagger.Module
import io.vasilenko.myrecipes.data.repo.RecipesRepository
import io.vasilenko.myrecipes.domain.repo.RecipesRepo

@Module
abstract class RecipesDataModule {

    @Binds
    abstract fun bindRecipesRepository(repository: RecipesRepository): RecipesRepo
}