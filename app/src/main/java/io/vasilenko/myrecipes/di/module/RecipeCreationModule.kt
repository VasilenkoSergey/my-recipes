package io.vasilenko.myrecipes.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.vasilenko.myrecipes.core.ViewModelKey
import io.vasilenko.myrecipes.presentation.creation.recipe.RecipeCreationViewModel

@Module
abstract class RecipeCreationModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecipeCreationViewModel::class)
    abstract fun bindRecipeViewModel(viewModel: RecipeCreationViewModel): ViewModel
}