package com.dev.ldhnam.kotlinsample.mvp.presenter

import com.dev.ldhnam.kotlinsample.api.RestService
import com.dev.ldhnam.kotlinsample.mvp.contract.MainContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(private val restService: RestService) : MainContract.Presenter {
    private var view: MainContract.View? = null
    private var disposables: CompositeDisposable = CompositeDisposable()

    companion object {
        val DEFAULT_AMOUNT: Int = 10
        val DEFAULT_REGION: String = "United States"
    }

    override fun getUsers() {
        disposables.add(restService.getUsers(DEFAULT_AMOUNT, DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    users ->
                    view?.showUsers(users)
                }))
    }

    override fun detachView() {
        view = null
        disposables.clear()
    }

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

}