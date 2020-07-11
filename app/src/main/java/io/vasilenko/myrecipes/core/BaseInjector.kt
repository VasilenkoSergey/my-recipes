package io.vasilenko.myrecipes.core

interface BaseInjector<T : Any> {

    fun inject(injected: T)
}