package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IReposCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IUsersCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.room.RoomGithubRepositoriesCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.room.RoomGithubUsersCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.room.RoomImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.db.Database
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.db.MIGRATION_1_2
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class CacheModule {
    @Named("dbName")
    @Provides
    fun dbName(): String = "database.db"

    @Named("cacheDir")
    @Provides
    fun cacheDir(): File = App.instance.cacheDir

    @Singleton
    @Provides
    fun database(app: App, @Named("dbName") dbName: String): Database = Room.databaseBuilder(
        app, Database::class.java,
        dbName
    )
        .addMigrations(MIGRATION_1_2)
        .build()

    @Singleton
    @Provides
    fun usersCache(database: Database): IUsersCache = RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun reposCache(database: Database): IReposCache = RoomGithubRepositoriesCache(database)

    @Singleton
    @Provides
    fun imagesCache(database: Database, @Named("cacheDir") cacheDir: File): IImageCache =
        RoomImageCache(database, cacheDir)
}