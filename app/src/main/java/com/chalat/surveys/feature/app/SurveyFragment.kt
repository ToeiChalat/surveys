package com.chalat.surveys.feature.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chalat.surveys.R
import kotlinx.android.synthetic.main.fragment_survey.*

class SurveyFragment : Fragment() {

    private var position: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getString(ARGS_SURVEY_FRAGMENT_POSITION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_survey, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        surveyTextView.text = "Position : $position"
    }

    companion object {

        private const val ARGS_SURVEY_FRAGMENT_POSITION = "com.chalat.survey.ARGS_SURVEY_FRAGMENT_POSITION"

        @JvmStatic
        fun newInstance(position: String) =
                SurveyFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARGS_SURVEY_FRAGMENT_POSITION, position)
                    }
                }
    }
}
