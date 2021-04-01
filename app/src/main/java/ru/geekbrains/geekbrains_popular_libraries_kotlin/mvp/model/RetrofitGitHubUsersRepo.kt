package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IGitHubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IUsersCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus


class RetrofitGitHubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IUsersCache
) : IGitHubUsersRepo {
    override fun getUsers(): Single<List<GitHubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.loadUsers()
                    .flatMap { users ->
                        cache.putUsers(users)
                    }
            } else {
                cache.getUsers()
            }
        }.subscribeOn(Schedulers.io())
}