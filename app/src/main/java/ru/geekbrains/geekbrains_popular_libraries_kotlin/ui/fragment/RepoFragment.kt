package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentRepoBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUserRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.RepoPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.RepoView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackButtonListener

private const val REPO_ARG = "RepoArg"

class RepoFragment : MvpAppCompatFragment(), RepoView, BackButtonListener {

    companion object {
        fun newInstance(repo: GitHubUserRepo): RepoFragment {
            val args = Bundle()
            args.putParcelable(REPO_ARG, repo)

            val fragment = RepoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var ui: FragmentRepoBinding? = null
    private val presenter: RepoPresenter by moxyPresenter {
        val currentRepo = arguments?.getParcelable<GitHubUserRepo>(REPO_ARG) as GitHubUserRepo
        RepoPresenter(
            App.instance.router,
            currentRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRepoBinding.inflate(inflater, container, false).also {
        ui = it
    }.root

    override fun onDestroyView() {
        ui = null
        super.onDestroyView()
    }

    override fun init(ownerName: String, repoName: String, language: String, forksNumber: String) {
        ui?.run {
            tvOwnerValue.text = ownerName
            tvRepoNameValue.text = repoName
            tvLanguageValue.text = language
            tvForksValue.text = forksNumber
        }
    }

    override fun backPressed() = presenter.backPressed()
}