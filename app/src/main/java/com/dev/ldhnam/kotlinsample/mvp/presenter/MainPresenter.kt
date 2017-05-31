package com.dev.ldhnam.kotlinsample.mvp.presenter

import android.util.Log
import com.dev.ldhnam.kotlinsample.api.RestService
import com.dev.ldhnam.kotlinsample.mvp.contract.MainContract
import com.dev.ldhnam.kotlinsample.mvp.model.User
import com.dev.ldhnam.kotlinsample.util.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainPresenter @Inject constructor(private val restService: RestService) : MainContract.Presenter {
    override fun updateUser() {
        val user: User = user!!
        val newUser: User = User(user.age, if (user.gender.equals("male", true)) "female" else "male", user.name, user.password, user.phone,
                user.photo, user.surname)
        view?.updateUserAt(1, newUser)
    }

    override fun getUser() {
        disposables.add(restService.getUser(DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user -> view?.addUser(user)
                }))
    }

    private var view: MainContract.View? = null
    private var disposables: CompositeDisposable = CompositeDisposable()
    private var itemCount: Int = 0
    private var size: Int = 0
    private var currentUsers: List<User>? = null
    private var user: User? = null

    companion object {
        val DEFAULT_AMOUNT: Int = 10
        val DEFAULT_REGION: String = "United States"

    }

    fun setUser(user: User) {
        this.user = user
    }

    fun setCurrentUsers(users: List<User>) {
        currentUsers = users
    }

    fun setSize(size: Int) {
        this.size = size
    }

    fun setItemCount(itemCount: Int) {
        this.itemCount = itemCount
    }

    override fun getUsers() {
        disposables.add(restService.getUsers(DEFAULT_AMOUNT, DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    users -> view?.addAllUser(users)
                    Log.d("users", users.size.toString())
                }))
    }

    override fun detachView() {
        view = null
        disposables.clear()
    }

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    fun setMultipleItems() {
        val currentUsers: List<User> = currentUsers!!
        val size: Int = size
        val newUsers = Utils.copyAndSwapName(currentUsers, size)
        restService.getUsers(DEFAULT_AMOUNT, DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map({ users ->
                    newUsers.addAll(users)
                    newUsers
                })
                .subscribe({users -> view?.setUsers(users)})
    }

    fun removeSingle() {
        val index = Utils.randomize(itemCount)
        if (index in 0..(size - 1)) {
            view?.removeUserAt(index)
        }
    }

}
