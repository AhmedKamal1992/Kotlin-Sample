package com.dev.ldhnam.kotlinsample

import android.app.Application
import com.dev.ldhnam.kotlinsample.di.component.AppComponent
import com.dev.ldhnam.kotlinsample.di.component.DaggerAppComponent
import com.dev.ldhnam.kotlinsample.di.module.AppModule
import com.facebook.drawee.backends.pipeline.Fresco

class AndroidApplication: Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        initDagger()
    }

    fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}