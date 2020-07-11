package io.vasilenko.myrecipes

import android.app.Application
import io.vasilenko.myrecipes.di.DI
import io.vasilenko.myrecipes.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        DI.appComponent = DaggerAppComponent.builder()
            .appContext(this)
            .build()
    }
}