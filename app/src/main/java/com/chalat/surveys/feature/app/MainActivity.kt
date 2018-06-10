package com.chalat.surveys.feature.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.chalat.surveys.BuildConfig
import com.chalat.surveys.R
import com.chalat.surveys.feature.survey.data.SurveyRemoteDataSource
import com.chalat.surveys.feature.survey.data.SurveyRepository
import com.chalat.surveys.network.NetworkProvider

class MainActivity : AppCompatActivity() {

    private val surveyRepository = SurveyRepository(
            SurveyRemoteDataSource(
                    BuildConfig.ACCESS_TOKEN,
                    NetworkProvider.provideSurveyService()
            )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment = supportFragmentManager.findFragmentById(R.id.contentContainer) as MainFragment?
        if (fragment == null) {
            fragment = MainFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .add(R.id.contentContainer, fragment)
                    .commit()
        }
        MainPresenter(
                surveyRepository,
                fragment
        )
    }
}
