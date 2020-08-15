package io.vasilenko.myrecipes.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.vasilenko.myrecipes.data.mapper.RecipesDataMapper
import io.vasilenko.myrecipes.data.repo.RecipesRepository
import io.vasilenko.myrecipes.domain.repo.RecipesRepo
import io.vasilenko.myrecipes.domain.usecase.LoadAllRecipesUseCase
import io.vasilenko.myrecipes.domain.usecase.UseCase
import io.vasilenko.myrecipes.presentation.mapper.RecipesModelMapper

@Module
class RecipesModule {

    @Provides
    fun provideRecipesMapper(): RecipesDataMapper {
        return RecipesDataMapper()
    }

    @Provides
    fun provideRecipesModelMapper(): RecipesModelMapper {
        return RecipesModelMapper()
    }
}

@Module
abstract class RecipesBindsModule {

    @Binds
    abstract fun repository(repository: RecipesRepository): RecipesRepo

    @Binds
    abstract fun loadAllRecipesUseCase(useCase: LoadAllRecipesUseCase): UseCase
}