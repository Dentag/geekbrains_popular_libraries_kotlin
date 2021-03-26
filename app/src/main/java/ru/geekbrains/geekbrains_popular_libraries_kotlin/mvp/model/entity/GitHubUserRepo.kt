package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GitHubUserRepo(
    @Expose val id: String,
    @Expose val name: String,
    @Expose val owner: GitHubUser,
    @Expose val language: String?,
    @Expose val forks: Int = 0
) : Parcelable