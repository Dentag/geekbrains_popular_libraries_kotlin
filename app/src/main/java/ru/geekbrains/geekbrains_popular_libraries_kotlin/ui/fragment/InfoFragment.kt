package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentInfoBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.InfoPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.InfoView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackButtonListener

private const val USERNAME_ARG = "userNameArg"

class InfoFragment : MvpAppCompatFragment(), InfoView, BackButtonListener {

    companion object {
        fun newInstance(userName: String): InfoFragment {
            val args = Bundle()
            args.putString(USERNAME_ARG, userName)

            val fragment = InfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val presenter: InfoPresenter by moxyPresenter {
        InfoPresenter(App.instance.router)
    }

    private var ui: FragmentInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentInfoBinding.inflate(inflater, container, false).also { ui = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui?.tvUser?.text = arguments?.getString(USERNAME_ARG)
    }

    override fun onDestroyView() {
        ui = null
        super.onDestroyView()
    }

    override fun backPressed() = presenter.backPressed()
}