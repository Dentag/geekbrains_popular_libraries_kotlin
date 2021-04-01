package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentInfoBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.InfoPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.InfoView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackButtonListener
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.adapter.ReposRVAdapter

class InfoFragment : MvpAppCompatFragment(), InfoView, BackButtonListener {
    companion object {
        private const val USER_ARG = "userArg"

        fun newInstance(user: GitHubUser) = InfoFragment().apply {
            arguments = Bundle().apply { putParcelable(USER_ARG, user) }
        }
    }

    private val presenter: InfoPresenter by moxyPresenter {
        val currentUser = arguments?.getParcelable<GitHubUser>(USER_ARG) as GitHubUser
        InfoPresenter(currentUser).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var ui: FragmentInfoBinding? = null
    private var adapter: ReposRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentInfoBinding.inflate(inflater, container, false).also {
        ui = it
    }.root

    override fun onDestroyView() {
        ui = null
        super.onDestroyView()
    }

    override fun backPressed() = presenter.backPressed()

    override fun init(userName: String) {
        ui?.tvUser?.text = userName
        ui?.rvRepos?.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposPresenter)
        ui?.rvRepos?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}