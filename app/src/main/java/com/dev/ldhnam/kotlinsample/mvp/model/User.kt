package com.dev.ldhnam.kotlinsample.mvp.model

data class User(val age: Int, val gender: String, val name: String,
                val password: String, val phone: String, val photo: String, val surname: String) {

    fun getDesc(): String = "$gender, $age, $phone"
}
