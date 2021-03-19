package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IGitHubUsersReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUserRepo


class RetrofitGitHubUsersReposRepo(private val api: IDataSource) : IGitHubUsersReposRepo {
    override fun getRepos(url: String): Single<List<GitHubUserRepo>> =
        api.getUserRepos(url).subscribeOn(Schedulers.io())
}