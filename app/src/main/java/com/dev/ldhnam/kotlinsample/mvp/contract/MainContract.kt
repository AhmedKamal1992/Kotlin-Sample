package com.dev.ldhnam.kotlinsample.mvp.contract

import com.dev.ldhnam.kotlinsample.mvp.base.BasePresenter
import com.dev.ldhnam.kotlinsample.mvp.base.BaseView
import com.dev.ldhnam.kotlinsample.mvp.model.User

interface MainContract {
    interface View: BaseView {
        fun showUsers(users: List<User>)
    }
    interface Presenter: BasePresenter<View> {
        fun getUsers()
    }
}
