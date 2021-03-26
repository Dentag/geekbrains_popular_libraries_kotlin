package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IDataSource
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IGitHubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser


class RetrofitGitHubUsersRepo(private val api: IDataSource) : IGitHubUsersRepo {
    override fun getUsers(): Single<List<GitHubUser>> = api.loadUsers().subscribeOn(Schedulers.io())
}