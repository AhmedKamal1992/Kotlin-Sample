package com.dev.ldhnam.kotlinsample.di.component

import com.dev.ldhnam.kotlinsample.di.module.MainModule
import com.dev.ldhnam.kotlinsample.di.scope.ActivityScope
import com.dev.ldhnam.kotlinsample.ui.activity.MainActivity
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(MainModule::class), dependencies = arrayOf(AppComponent::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}