package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.modules

import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IGitHubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IGitHubUsersReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IUsersCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.RetrofitGitHubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.RetrofitGitHubUsersReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun userRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUsersCache
    ): IGitHubUsersRepo = RetrofitGitHubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun reposRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IReposCache
    ): IGitHubUsersReposRepo = RetrofitGitHubUsersReposRepo(api, networkStatus, cache)
}