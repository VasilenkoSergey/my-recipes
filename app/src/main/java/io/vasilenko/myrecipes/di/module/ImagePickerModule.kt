package io.vasilenko.myrecipes.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.vasilenko.myrecipes.core.ViewModelKey
import io.vasilenko.myrecipes.domain.usecase.CreateImageUseCase
import io.vasilenko.myrecipes.domain.usecase.DeleteImageUseCase
import io.vasilenko.myrecipes.domain.usecase.GetImageDataUseCase
import io.vasilenko.myrecipes.domain.usecase.UseCase
import io.vasilenko.myrecipes.presentation.imagepicker.ImagePickerViewModel

@Module
abstract class ImagePickerModule {

    @Binds
    abstract fun createImageUseCase(useCase: CreateImageUseCase): UseCase

    @Binds
    abstract fun getImageDataUseCase(useCase: GetImageDataUseCase): UseCase

    @Binds
    abstract fun deleteImageUseCase(useCase: DeleteImageUseCase): UseCase

    @Binds
    @IntoMap
    @ViewModelKey(ImagePickerViewModel::class)
    abstract fun bindImagePickerViewModel(viewModel: ImagePickerViewModel): ViewModel
}