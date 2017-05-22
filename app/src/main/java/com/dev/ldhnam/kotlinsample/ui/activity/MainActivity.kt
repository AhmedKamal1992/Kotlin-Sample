package com.dev.ldhnam.kotlinsample.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.dev.ldhnam.kotlinsample.R
import com.dev.ldhnam.kotlinsample.di.component.DaggerMainComponent
import com.dev.ldhnam.kotlinsample.mvp.base.BaseActivity
import com.dev.ldhnam.kotlinsample.mvp.contract.MainContract
import com.dev.ldhnam.kotlinsample.mvp.model.User
import com.dev.ldhnam.kotlinsample.mvp.presenter.MainPresenter
import com.dev.ldhnam.kotlinsample.ui.adapter.UserAdapter
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @BindView(R.id.rv_user) lateinit var rvUser: RecyclerView
    lateinit var userAdapter: UserAdapter

    @Inject lateinit var mainPresenter: MainPresenter

    override fun showUsers(users: List<User>) {
        userAdapter.setUsers(users)
    }

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this)
        userAdapter = UserAdapter()
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.adapter = userAdapter
        mainPresenter.attachView(this)
        mainPresenter.getUsers()
    }
}
