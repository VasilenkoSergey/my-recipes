package io.vasilenko.myrecipes.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.vasilenko.myrecipes.core.ViewModelKey
import io.vasilenko.myrecipes.data.mapper.CategoriesDataMapper
import io.vasilenko.myrecipes.data.mapper.RecipesDataMapper
import io.vasilenko.myrecipes.data.repo.CategoriesRepository
import io.vasilenko.myrecipes.data.repo.RecipesRepository
import io.vasilenko.myrecipes.domain.repo.CategoriesRepo
import io.vasilenko.myrecipes.domain.repo.RecipesRepo
import io.vasilenko.myrecipes.domain.usecase.CreateRecipeUseCase
import io.vasilenko.myrecipes.domain.usecase.LoadAllCategoriesUseCase
import io.vasilenko.myrecipes.domain.usecase.UseCase
import io.vasilenko.myrecipes.presentation.creation.recipe.RecipeCreationViewModel
import io.vasilenko.myrecipes.presentation.mapper.RecipesModelMapper

@Module
class RecipeModule {

    @Provides
    fun provideRecipesDataMapper(): RecipesDataMapper {
        return RecipesDataMapper()
    }

    @Provides
    fun provideRecipesModelMapper(): RecipesModelMapper {
        return RecipesModelMapper()
    }

    @Provides
    fun provideCategoriesDataMapper(): CategoriesDataMapper {
        return CategoriesDataMapper()
    }
}

@Module
abstract class RecipeBindsModule {

    @Binds
    abstract fun repository(repository: RecipesRepository): RecipesRepo

    @Binds
    abstract fun categoriesRepository(categoriesRepository: CategoriesRepository): CategoriesRepo

    @Binds
    abstract fun createRecipeUseCase(useCase: CreateRecipeUseCase): UseCase

    @Binds
    abstract fun loadAllCategoriesUseCase(useCase: LoadAllCategoriesUseCase): UseCase

    @Binds
    @IntoMap
    @ViewModelKey(RecipeCreationViewModel::class)
    abstract fun bindRecipeViewModel(viewModel: RecipeCreationViewModel): ViewModel
}