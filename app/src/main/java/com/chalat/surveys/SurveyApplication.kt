package com.chalat.surveys

import android.app.Application
import android.content.Context
import com.chalat.surveys.network.TokenManager

/**
 *
 * Created by Chalat Chansima on 6/11/18.
 *
 */
class SurveyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        TokenManager.setSharedPreference(
                getSharedPreferences("token", Context.MODE_PRIVATE)
        )
    }

}