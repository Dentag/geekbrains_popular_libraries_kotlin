package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.InfoView

class InfoPresenter(private val router: Router, private val user: GitHubUser?) :
    MvpPresenter<InfoView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user?.let { viewState.init(it.login) }
    }
}