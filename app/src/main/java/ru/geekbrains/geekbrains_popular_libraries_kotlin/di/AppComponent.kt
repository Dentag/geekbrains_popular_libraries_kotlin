package ru.geekbrains.geekbrains_popular_libraries_kotlin.di

import dagger.Component
import ru.geekbrains.geekbrains_popular_libraries_kotlin.di.modules.*
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.InfoPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.MainPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.RepoPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UsersPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.activity.MainActivity
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter.UsersRVAdapter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class,
        SchedulerModule::class,
        ImageModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(infoPresenter: InfoPresenter)
    fun inject(repoPresenter: RepoPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}