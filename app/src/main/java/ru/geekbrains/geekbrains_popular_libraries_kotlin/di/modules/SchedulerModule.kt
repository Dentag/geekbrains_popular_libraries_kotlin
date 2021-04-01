package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

@Module
class SchedulerModule {

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}