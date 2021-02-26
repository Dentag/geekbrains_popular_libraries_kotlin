package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.CountersModel
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.MainView

class MainPresenter(private val mainView: MainView) {
    private val model = CountersModel()

    fun firstBtnClicked() {
        val nextValue = model.next(0)
        mainView.setFirstButtonText(nextValue.toString())
    }

    fun secondBtnClicked() {
        val nextValue = model.next(1)
        mainView.setSecondButtonText(nextValue.toString())
    }

    fun thirdBtnClicked() {
        val nextValue = model.next(2)
        mainView.setThirdButtonText(nextValue.toString())
    }
}