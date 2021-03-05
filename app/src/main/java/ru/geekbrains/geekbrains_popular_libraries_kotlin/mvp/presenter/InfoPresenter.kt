package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.InfoView

class InfoPresenter(private val router: Router) : MvpPresenter<InfoView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}