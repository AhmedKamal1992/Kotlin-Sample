package com.dev.ldhnam.kotlinsample.mvp.base

interface BasePresenter<in T: BaseView> {
    fun attachView(view: T)
    fun detachView()
}