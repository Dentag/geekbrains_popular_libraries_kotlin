package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GitHubUserRepo(
    @Expose var id: String,
    @Expose var name: String,
    @Expose var forksCount: Int
) : Parcelable