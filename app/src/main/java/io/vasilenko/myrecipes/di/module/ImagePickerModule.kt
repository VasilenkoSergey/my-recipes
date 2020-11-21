package io.vasilenko.myrecipes.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.vasilenko.myrecipes.core.presentation.viewmodel.ViewModelKey
import io.vasilenko.myrecipes.presentation.imagepicker.ImagePickerViewModel

@Module
abstract class ImagePickerModule {

    @Binds
    @IntoMap
    @ViewModelKey(ImagePickerViewModel::class)
    abstract fun bindImagePickerViewModel(viewModel: ImagePickerViewModel): ViewModel
}