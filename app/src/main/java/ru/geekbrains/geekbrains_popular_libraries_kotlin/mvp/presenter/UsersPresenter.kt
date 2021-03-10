package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.GitHubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.navigation.IScreens
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UsersView

class UsersPresenter(
    private val usersRepo: GitHubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) :
    MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos].login
            router.navigateTo(screens.info(user))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}