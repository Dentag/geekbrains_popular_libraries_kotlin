package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IGitHubUsersReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.navigation.IScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.InfoView

class InfoPresenter(
    private val reposRepo: IGitHubUsersReposRepo,
    private val router: Router,
    private val screens: IScreens,
    private val user: GitHubUser,
    private val uiScheduler: Scheduler
) :
    MvpPresenter<InfoView>() {

    val reposPresenter = ReposListPresenter()

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(user.login)
        loadData()

        reposPresenter.itemClickListener = { itemView ->
            val repo = reposPresenter.repos[itemView.pos]
            router.navigateTo(screens.repo(repo))
        }
    }

    private fun loadData() {
        reposRepo.getRepos(user.reposUrl).observeOn(uiScheduler).subscribe({ result ->
            reposPresenter.repos.clear()
            reposPresenter.repos.addAll(result)
            viewState.updateList()
        }, { error ->
            error.printStackTrace()
        })
    }
}