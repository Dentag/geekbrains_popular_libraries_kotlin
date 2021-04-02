package ru.geekbrains.geekbrains_popular_libraries_kotlin.di.modules

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.image.IImageLoader
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.image.GlideImageLoader
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun imageLoader(
        cache: IImageCache,
        networkStatus: INetworkStatus,
        @Named("ui") mainScheduler: Scheduler
    ): IImageLoader<ImageView> = GlideImageLoader(cache, networkStatus, mainScheduler)
}