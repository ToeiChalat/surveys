package com.chalat.surveys.network

import android.content.SharedPreferences

/**
 *
 * Created by Chalat Chansima on 6/11/18.
 *
 */
object TokenManager {

    private const val KEY_ACCESS_TOKEN = "com.chalat.surveys.KEY_ACCESS_TOKEN"

    private lateinit var sharedPreferences: SharedPreferences

    fun setSharedPreference(sharedPreferences: SharedPreferences) {
        this.sharedPreferences = sharedPreferences
    }

    fun getToken(): String {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, "")
    }

    fun setToken(accessToken: String) {
        sharedPreferences.edit()
                .putString(KEY_ACCESS_TOKEN, accessToken)
                .apply()
    }

}
