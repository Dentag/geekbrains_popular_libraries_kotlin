package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser


class GitHubUsersRepo {
    private val repositories = listOf(
        GitHubUser("login1"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5")
    )

    fun getUsers(): Observable<List<GitHubUser>> = Observable.just(repositories).subscribeOn(
        Schedulers.io()
    )

}