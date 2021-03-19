package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.image

interface IImageLoader<T> {
    fun load(url: String, container: T)
}