package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache

import io.reactivex.rxjava3.core.Single

interface IImageCache {
    fun putImage(url: String, bytes: ByteArray): Single<Boolean>
    fun getImage(url: String): Single<ByteArray>
}