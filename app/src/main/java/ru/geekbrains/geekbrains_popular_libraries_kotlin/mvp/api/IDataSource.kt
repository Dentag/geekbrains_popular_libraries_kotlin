package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUserRepo

interface IDataSource {
    @GET("users")
    fun loadUsers(): Single<List<GitHubUser>>

    @GET
    fun getUserRepos(@Url url: String): Single<List<GitHubUserRepo>>
}