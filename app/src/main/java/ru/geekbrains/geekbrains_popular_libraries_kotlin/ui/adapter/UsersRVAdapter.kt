package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.ItemUserBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IUserListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.IUserItemView

class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val ui: ItemUserBinding) : RecyclerView.ViewHolder(ui.root),
        IUserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(ui) {
            tvLogin.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ).apply {
        itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount(): Int = presenter.getCount()
}