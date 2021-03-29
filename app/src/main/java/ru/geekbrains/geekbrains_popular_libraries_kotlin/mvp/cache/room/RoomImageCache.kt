package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.room

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.RoomImage
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.room.db.Database
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

class RoomImageCache(private val db: Database, cacheDir: File) : IImageCache {
    private val dir = "$cacheDir/img/"

    override fun putImage(url: String, bytes: ByteArray): Single<Boolean> =
        Single.fromCallable {
            File(dir).mkdir()
            val imageFile = File(dir, UUID.nameUUIDFromBytes(bytes).toString())
            val stream = FileOutputStream(imageFile.path)
            stream.write(bytes)
            val roomImage = RoomImage(url, imageFile.path)
            db.imageDao.insert(roomImage)
            true
        }.subscribeOn(Schedulers.io())


    override fun getImage(url: String): Single<ByteArray> = Single.fromCallable {
        val roomImage = db.imageDao.findByUrl(url)
        roomImage?.let {
            val stream = FileInputStream(File(it.localPath))
            stream.readBytes()
        } ?: ByteArray(0)
    }.subscribeOn(Schedulers.io())
}