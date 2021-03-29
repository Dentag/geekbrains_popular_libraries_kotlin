package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IGitHubUsersReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUserRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus


class RetrofitGitHubUsersReposRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IReposCache
) : IGitHubUsersReposRepo {
    override fun getRepos(user: GitHubUser): Single<List<GitHubUserRepo>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUserRepos(user.reposUrl)
                    .flatMap { repositories ->
                        cache.putRepos(repositories, user)
                    }
                    .subscribeOn(Schedulers.io())
            } else {
                cache.getRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}