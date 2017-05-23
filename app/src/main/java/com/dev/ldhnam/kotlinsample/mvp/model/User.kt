package com.dev.ldhnam.kotlinsample.mvp.model

import android.os.Bundle
import android.text.TextUtils
import com.dev.ldhnam.kotlinsample.util.Constant

data class User(val age: Int, val gender: String, val name: String,
                val password: String, val phone: String, val photo: String, val surname: String) {

    fun getDesc(): String = "$gender, $age, $phone"

    fun getId(): String = password

    fun getNameCustom(): String = "$name $surname"

    companion object {

        fun areContentsTheSame(oldUser: User, newUser: User): Boolean = TextUtils.equals(oldUser.name, newUser.name) &&
                oldUser.age == newUser.age &&
                TextUtils.equals(oldUser.gender, newUser.gender)

        fun areItemsTheSame(user1: User, user2: User): Boolean = TextUtils.equals(user1.getId(), user2.getId())

        fun compare(user1: User, user2: User): Int {
            val nameCompare: Int = user1.getNameCustom().compareTo(user2.getNameCustom(), true)
            val ageCompare: Int = user1.age - user2.age
            val genderCompare: Int = user1.gender.compareTo(user2.gender, true)
            return if (nameCompare != 0) nameCompare else (if (ageCompare != 0) ageCompare else genderCompare)
        }

        fun getChangePayload(oldUser: User, newUser: User): Any? {
            val bundle: Bundle = Bundle()
            if (!TextUtils.equals(oldUser.photo, newUser.photo))
                bundle.putString(Constant.KEY_AVATAR, newUser.photo)
            if (!TextUtils.equals(oldUser.getNameCustom(), newUser.getNameCustom()))
                bundle.putString(Constant.KEY_NAME, newUser.getNameCustom())
            if (!TextUtils.equals(oldUser.getDesc(), newUser.getDesc()))
                bundle.putString(Constant.KEY_DESC, newUser.getDesc())
            if (bundle.size() == 0) {
                return null
            }
            return bundle
        }
    }
}
