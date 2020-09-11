package io.vasilenko.myrecipes.core.di

interface BaseInjector<T : Any> {

    fun inject(injected: T)
}