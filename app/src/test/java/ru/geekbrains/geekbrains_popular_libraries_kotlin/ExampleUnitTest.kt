package ru.geekbrains.geekbrains_popular_libraries_kotlin

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Test
import org.junit.Assert.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random.Default.nextInt


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {

    @Test
    fun execFlatMap() {
        val testScheduler = TestScheduler()

        Observable.just("1", "2", "3", "4", "5")
            .switchMap {
                val delay = kotlin.random.Random.Default.nextInt(1000).toLong()
                println("$it added")
                return@switchMap Observable.just(it + "x")
                    .delay(delay, TimeUnit.MILLISECONDS, testScheduler)
            }.toList()
            .subscribe({ s ->
                println("onNext: $s")
            }, {
                println("onError: ${it.message}")
            })
        testScheduler.advanceTimeBy(1, TimeUnit.MINUTES)
    }
}