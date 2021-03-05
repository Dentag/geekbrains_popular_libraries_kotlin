package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list

import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.IItemView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.IUserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<IUserItemView>