package com.dev.ldhnam.kotlinsample.mvp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.dev.ldhnam.kotlinsample.AndroidApplication
import com.dev.ldhnam.kotlinsample.di.component.AppComponent

abstract class BaseActivity : AppCompatActivity() {
    protected abstract val layoutRes: Int

    private lateinit var unbinder: Unbinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        unbinder = ButterKnife.bind(this)
    }

    override fun onDestroy() {
        unbinder.unbind()
        super.onDestroy()
    }

    fun getAppComponent() : AppComponent? = (applicationContext as AndroidApplication).appComponent
}
