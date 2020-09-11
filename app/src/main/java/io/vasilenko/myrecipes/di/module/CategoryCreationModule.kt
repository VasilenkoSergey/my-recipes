package io.vasilenko.myrecipes.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.vasilenko.myrecipes.core.ViewModelKey
import io.vasilenko.myrecipes.presentation.creation.category.CategoryCreationViewModel

@Module
abstract class CategoryCreationModule {

    @Binds
    @IntoMap
    @ViewModelKey(CategoryCreationViewModel::class)
    abstract fun bindCatalogViewModel(viewModel: CategoryCreationViewModel): ViewModel
}