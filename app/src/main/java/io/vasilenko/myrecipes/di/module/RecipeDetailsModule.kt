package io.vasilenko.myrecipes.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.vasilenko.myrecipes.core.presentation.viewmodel.ViewModelKey
import io.vasilenko.myrecipes.presentation.recipedetails.RecipeDetailsViewModel

@Module
abstract class RecipeDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecipeDetailsViewModel::class)
    abstract fun bindRecipeDetailsViewModel(viewModel: RecipeDetailsViewModel): ViewModel
}