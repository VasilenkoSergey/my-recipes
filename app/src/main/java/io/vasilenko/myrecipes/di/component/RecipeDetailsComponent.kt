package io.vasilenko.myrecipes.di.component

import dagger.Component
import io.vasilenko.myrecipes.core.FeatureScope
import io.vasilenko.myrecipes.core.ViewModelFactory
import io.vasilenko.myrecipes.di.DI
import io.vasilenko.myrecipes.di.module.RecipeDetailsBindsModule

@FeatureScope
@Component(modules = [RecipeDetailsBindsModule::class])
interface RecipeDetailsComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        fun build(): RecipeDetailsComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerRecipeDetailsComponent.builder()
                .build()
        }
    }
}