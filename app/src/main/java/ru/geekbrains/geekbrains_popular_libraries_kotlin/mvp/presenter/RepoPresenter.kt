package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUserRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.RepoView

class RepoPresenter(
    private val router: Router,
    private val repo: GitHubUserRepo
) : MvpPresenter<RepoView>() {
    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(repo.owner.login, repo.name, repo.language ?: "", repo.forks.toString())
    }
}