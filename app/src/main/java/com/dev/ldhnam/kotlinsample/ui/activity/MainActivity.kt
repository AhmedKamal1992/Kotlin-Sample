package com.dev.ldhnam.kotlinsample.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
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
    override fun removeUserAt(index: Int) {
        userAdapter.rxSortedList.removeItemAt(index)
    }

    override fun setUsers(users: ArrayList<User>) {
        userAdapter.rxSortedList.addAll(users)
    }

    override fun updateUserAt(index: Int, user: User) {
        userAdapter.rxSortedList.updateItemAt(1, user)
    }

    override fun addUser(user: User) {
        userAdapter.rxSortedList.add(user)
    }

    override fun addAllUser(users: List<User>) {
        userAdapter.rxSortedList.addAll(users)
    }

    @BindView(R.id.rv_user) lateinit var rvUser: RecyclerView
    lateinit var userAdapter: UserAdapter

    @Inject lateinit var mainPresenter: MainPresenter

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this)
        userAdapter = UserAdapter()
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.setHasFixedSize(true)
        rvUser.adapter = userAdapter
        mainPresenter.attachView(this)
        mainPresenter.setAdapter(userAdapter)
        mainPresenter.getUsers()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_add_single -> {
                mainPresenter.getUser()
            }
            R.id.action_add_multiple -> {
                mainPresenter.getUsers()
            }
            R.id.action_set_multiple_items -> {
                mainPresenter.setMultipleItems()
            }
            R.id.action_update_single -> {
                mainPresenter.updateUser()
            }
            R.id.action_remove_single -> {
                mainPresenter.removeSingle()
            }
            R.id.action_clear -> {
                userAdapter.rxSortedList.clear()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        userAdapter.rxSortedList.clearSubscribe()
        mainPresenter.detachView()
    }
}
