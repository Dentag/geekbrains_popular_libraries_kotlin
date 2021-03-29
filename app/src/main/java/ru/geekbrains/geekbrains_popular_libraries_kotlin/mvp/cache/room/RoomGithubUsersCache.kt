package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.room

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IUsersCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.RoomGithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.db.Database

class RoomGithubUsersCache(val db: Database) : IUsersCache {
    override fun putUsers(users: List<GitHubUser>): Single<List<GitHubUser>> =
        Single.fromCallable {
            val roomUsers = users.map { user ->
                RoomGithubUser(
                    user.id,
                    user.login,
                    user.avatarUrl,
                    user.reposUrl
                )
            }
            db.userDao.insert(roomUsers)
            users
        }

    override fun getUsers(): Single<List<GitHubUser>> =
        Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GitHubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }
}