package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUserRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.RepoView
import javax.inject.Inject

class RepoPresenter(private val repo: GitHubUserRepo) : MvpPresenter<RepoView>() {
    @Inject
    lateinit var router: Router

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(repo.id, repo.name, repo.forksCount.toString())
    }
}