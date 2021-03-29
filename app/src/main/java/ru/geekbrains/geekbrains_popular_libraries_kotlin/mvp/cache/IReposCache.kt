package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUserRepo

interface IReposCache {
    fun putRepos(repositories: List<GitHubUserRepo>, user: GitHubUser): Single<List<GitHubUserRepo>>
    fun getRepos(user: GitHubUser): Single<List<GitHubUserRepo>>
}