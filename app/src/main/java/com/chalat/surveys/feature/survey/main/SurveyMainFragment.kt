package com.chalat.surveys.feature.survey.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chalat.surveys.R
import com.chalat.surveys.feature.survey.data.Survey
import com.chalat.surveys.feature.survey.detail.SurveyDetailActivity
import com.chalat.surveys.utils.setImage
import kotlinx.android.synthetic.main.fragment_survey_main.*

class SurveyMainFragment : Fragment() {

    private var surveyData: Survey? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            surveyData = it.getParcelable(ARGS_SURVEY_MAIN_DATA)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_survey_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        surveyData?.let {survey ->
            surveyMainNameTextView.text = survey.surveyName
            surveyMainDescriptionTextView.text = survey.surveyDescription
            surveyMainBackgroundImageView.setImage(survey.surveyImageCoverUrl + "l")
        }
        surveyMainTakeSurveyButton.setOnClickListener {
            startActivity(Intent(context, SurveyDetailActivity::class.java))
        }
    }

    companion object {

        private const val ARGS_SURVEY_MAIN_DATA = "com.chalat.survey.ARGS_SURVEY_MAIN_DATA"

        @JvmStatic
        fun newInstance(survey: Survey) =
                SurveyMainFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARGS_SURVEY_MAIN_DATA, survey)
                    }
                }
    }
}
