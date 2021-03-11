package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentInfoBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.InfoPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.InfoView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackButtonListener

private const val USER_ARG = "userArg"

class InfoFragment : MvpAppCompatFragment(), InfoView, BackButtonListener {

    companion object {
        fun newInstance(user: GitHubUser): InfoFragment {
            val args = Bundle()
            args.putParcelable(USER_ARG, user)

            val fragment = InfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val presenter: InfoPresenter by moxyPresenter {
        InfoPresenter(App.instance.router, currentUser)
    }

    private var ui: FragmentInfoBinding? = null
    private var currentUser: GitHubUser? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        currentUser = arguments?.getParcelable(USER_ARG)
    }

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
    }
}