package com.dev.ldhnam.kotlinsample.api

import com.dev.ldhnam.kotlinsample.mvp.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestService {

    @GET("/api/?ext")
    fun getUser(@Query("region") region: String): Observable<User>

    @GET("/api/?ext")
    fun getUsers(@Query("amount") amount: Int, @Query("region") region: String): Observable<List<User>>
}