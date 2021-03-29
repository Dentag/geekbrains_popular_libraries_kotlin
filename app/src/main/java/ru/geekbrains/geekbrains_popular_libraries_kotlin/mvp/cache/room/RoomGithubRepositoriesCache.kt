package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.room

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GitHubUserRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.RoomGithubRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.db.Database

class RoomGithubRepositoriesCache(val db: Database) : IReposCache {
    override fun putRepos(
        repositories: List<GitHubUserRepo>,
        user: GitHubUser
    ): Single<List<GitHubUserRepo>> =
        Single.fromCallable {
            val roomUser = user.login.let { db.userDao.findByLogin(it) }
                ?: throw RuntimeException("No such user in cache")
            val roomRepos = repositories.map {
                RoomGithubRepository(
                    it.id,
                    it.name,
                    it.forksCount,
                    roomUser.id
                )
            }
            db.repositoryDao.insert(roomRepos)
            repositories
        }

    override fun getRepos(user: GitHubUser): Single<List<GitHubUserRepo>> = Single.fromCallable {
        val roomUser = user.login.let { db.userDao.findByLogin(it) }
            ?: throw RuntimeException("No such user in cache")
        db.repositoryDao.findForUser(roomUser.id)
            .map { GitHubUserRepo(it.id, it.name, it.forksCount) }
    }
}