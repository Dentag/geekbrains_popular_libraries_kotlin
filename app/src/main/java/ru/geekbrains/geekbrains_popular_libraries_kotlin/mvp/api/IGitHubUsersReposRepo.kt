package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUserRepo

interface IGitHubUsersReposRepo {
    fun getRepos(url: String): Single<List<GitHubUserRepo>>
}