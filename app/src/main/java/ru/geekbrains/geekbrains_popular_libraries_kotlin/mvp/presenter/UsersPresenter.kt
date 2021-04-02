package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.api.IGitHubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.navigation.IScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UsersView
import javax.inject.Inject
import javax.inject.Named

class UsersPresenter() :
    MvpPresenter<UsersView>() {
    @Inject
    lateinit var usersRepo: IGitHubUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    @field: Named("ui")
    lateinit var uiScheduler: Scheduler

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.info(user))
        }
    }

    private fun loadData() {
        usersRepo.getUsers().observeOn(uiScheduler).subscribe({ result ->
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(result)
            viewState.updateList()
        }, { error ->
            error.printStackTrace()
        })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}