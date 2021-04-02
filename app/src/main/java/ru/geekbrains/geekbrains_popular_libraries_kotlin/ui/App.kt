package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui

import android.app.Application
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.AppComponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.DaggerAppComponent
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.modules.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
