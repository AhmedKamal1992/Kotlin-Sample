package com.dev.ldhnam.kotlinsample.di.component

import com.dev.ldhnam.kotlinsample.api.RestService
import com.dev.ldhnam.kotlinsample.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun restService(): RestService
}