package io.vasilenko.myrecipes.di.component

import dagger.BindsInstance
import dagger.Component
import io.vasilenko.myrecipes.core.di.FeatureScope
import io.vasilenko.myrecipes.core.presentation.viewmodel.ViewModelFactory
import io.vasilenko.myrecipes.core.files.FileManager
import io.vasilenko.myrecipes.di.DI
import io.vasilenko.myrecipes.di.module.ImagePickerModule

@FeatureScope
@Component(modules = [ImagePickerModule::class])
interface ImagePickerComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun fileManager(fileManager: FileManager): Builder

        fun build(): ImagePickerComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerImagePickerComponent.builder()
                .fileManager(files())
                .build()
        }
    }
}