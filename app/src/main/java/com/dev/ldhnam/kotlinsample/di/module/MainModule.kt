package com.dev.ldhnam.kotlinsample.di.module

import com.dev.ldhnam.kotlinsample.api.RestService
import com.dev.ldhnam.kotlinsample.di.scope.ActivityScope
import com.dev.ldhnam.kotlinsample.mvp.contract.MainContract
import com.dev.ldhnam.kotlinsample.mvp.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @ActivityScope
    fun provideMainPresenter(restService: RestService): MainContract.Presenter {
        return MainPresenter(restService)
    }

}