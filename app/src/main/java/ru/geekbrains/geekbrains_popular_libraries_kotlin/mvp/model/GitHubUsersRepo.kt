package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model

import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser

class GitHubUsersRepo {
    private val repositories = listOf(
        GitHubUser("login1"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5")
    )

    fun getUsers(): List<GitHubUser> = repositories
}