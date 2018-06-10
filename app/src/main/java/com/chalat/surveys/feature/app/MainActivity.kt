package com.chalat.surveys.feature.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.chalat.surveys.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit()
        }
    }
}
