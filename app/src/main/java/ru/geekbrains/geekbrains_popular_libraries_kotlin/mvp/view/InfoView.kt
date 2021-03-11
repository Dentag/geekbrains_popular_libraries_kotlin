package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface InfoView : MvpView {
    fun init(userName: String)
}