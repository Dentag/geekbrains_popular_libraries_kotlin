package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser

interface IUsersCache {
    fun putUsers(users: List<GitHubUser>) : Single<List<GitHubUser>>
    fun getUsers() : Single<List<GitHubUser>>
}