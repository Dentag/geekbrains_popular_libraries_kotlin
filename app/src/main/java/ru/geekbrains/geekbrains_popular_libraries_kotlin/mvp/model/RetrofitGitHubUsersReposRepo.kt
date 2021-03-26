package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IGitHubUsersReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUserRepo


class RetrofitGitHubUsersReposRepo(private val api: IDataSource) : IGitHubUsersReposRepo {
    override fun getRepos(user: GitHubUser): Single<List<GitHubUserRepo>> =
        api.getUserRepos(user.reposUrl).subscribeOn(Schedulers.io())
}