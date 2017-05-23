package com.dev.ldhnam.kotlinsample.util

import com.dev.ldhnam.kotlinsample.mvp.model.User
import java.util.*
import kotlin.collections.ArrayList

object Utils {
    fun randomize(min: Int, max: Int): Int {
        if (min == max) {
            return min
        }
        if (max < min) {
            throw IllegalArgumentException("Max should be greater than min")
        }

        val range = max - min
        return (Math.random() * range).toInt() + min
    }

    fun randomize(max: Int): Int {
        return Random().nextInt(max)
    }

    fun copyAndSwapName(users: List<User>, size: Int): ArrayList<User> {
        val newUsers = ArrayList<User>(size)
        for (i in 0..size - 1) {
            val (age, gender, name, password1, phone, photo, surname) = users[i]
            val newUser = User(age, gender, surname, password1, phone, photo, name)
            newUsers.add(newUser)
        }
        Collections.shuffle(newUsers)
        return newUsers
    }
}