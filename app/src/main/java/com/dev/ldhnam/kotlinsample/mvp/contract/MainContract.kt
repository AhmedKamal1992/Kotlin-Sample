package com.dev.ldhnam.kotlinsample.mvp.contract

import com.dev.ldhnam.kotlinsample.mvp.base.BasePresenter
import com.dev.ldhnam.kotlinsample.mvp.base.BaseView
import com.dev.ldhnam.kotlinsample.mvp.model.User

interface MainContract {
    interface View: BaseView {
        fun addUser(user: User)
        fun addAllUser(users: List<User>)
        fun setUsers(users: ArrayList<User>)
        fun updateUserAt(index: Int, user: User)
        fun removeUserAt(index: Int)
    }
    interface Presenter: BasePresenter<View> {
        fun getUsers()
        fun getUser()
        fun updateUser()
    }
}
