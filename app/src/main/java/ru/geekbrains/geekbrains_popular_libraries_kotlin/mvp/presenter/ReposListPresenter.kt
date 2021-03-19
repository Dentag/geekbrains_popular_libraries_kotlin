package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUserRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IRepoListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.IRepoItemView

class ReposListPresenter : IRepoListPresenter {
    val repos = mutableListOf<GitHubUserRepo>()
    override var itemClickListener: ((IRepoItemView) -> Unit)? = null

    override fun bindView(view: IRepoItemView) {
        val repo = repos[view.pos]
        view.setInfo(repo.name)
    }

    override fun getCount() = repos.size
}