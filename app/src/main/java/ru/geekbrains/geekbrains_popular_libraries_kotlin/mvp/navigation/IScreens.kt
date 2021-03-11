package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.navigation

import com.github.terrakok.cicerone.Screen
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser

interface IScreens {
    fun users(): Screen
    fun info(user: GitHubUser): Screen
}