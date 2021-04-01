package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.db

import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.RoomGithubRepository
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.RoomGithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.RoomImage
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.dao.ImageDao
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.dao.RepositoryDao
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.dao.UserDao

@androidx.room.Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class, RoomImage::class],
    version = 2
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao
}

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `RoomImage` (`url` TEXT NOT NULL, `localPath` TEXT NOT NULL, PRIMARY KEY(`url`))")
    }
}