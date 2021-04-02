package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IGitHubUsersReposRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.navigation.IScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.InfoView
import javax.inject.Inject
import javax.inject.Named

class InfoPresenter(private val user: GitHubUser) :
    MvpPresenter<InfoView>() {
    @Inject
    lateinit var reposRepo: IGitHubUsersReposRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    @field: Named("ui")
    lateinit var uiScheduler: Scheduler

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
        reposRepo.getRepos(user).observeOn(uiScheduler).subscribe({ result ->
            reposPresenter.repos.clear()
            reposPresenter.repos.addAll(result)
            viewState.updateList()
        }, { error ->
            error.printStackTrace()
        })
    }
}