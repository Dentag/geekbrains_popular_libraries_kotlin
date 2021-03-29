package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.rxjava3.core.Scheduler
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.cache.IImageCache
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.image.IImageLoader
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.network.INetworkStatus
import java.io.ByteArrayOutputStream

class GlideImageLoader(
    private val cache: IImageCache,
    private val networkStatus: INetworkStatus,
    private val mainScheduler: Scheduler
) :
    IImageLoader<ImageView> {
    override fun load(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    networkStatus.isOnlineSingle().subscribe { isOnline ->
                        if (isOnline) {
                            val stream = ByteArrayOutputStream()
                            resource?.compress(Bitmap.CompressFormat.PNG, 100, stream)
                            cache.putImage(url, stream.toByteArray())
                                .observeOn(mainScheduler).subscribe({
                                    container.setImageBitmap(resource)
                                }, {

                                })

                        } else {
                            cache.getImage(url).observeOn(mainScheduler)
                                .subscribe { bytes ->
                                    val img = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                                    container.setImageBitmap(img)
                                }
                        }
                    }
                    return true
                }
            })
            .into(container)
    }
}