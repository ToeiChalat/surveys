package com.chalat.surveys.feature.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chalat.surveys.R
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainRefreshAction.setOnClickListener { showToast("Refresh") }
        mainMenuAction.setOnClickListener { showToast("Menu") }
        mainSurveyViewPager.apply {
            adapter = SurveyViewPagerAdapter(childFragmentManager)
        }
        val circleRadius = resources.getDimensionPixelSize(R.dimen.circle_page_indicator_size).toFloat()
        mainSurveyIndicator.apply {
            radius = circleRadius
            setViewPager(mainSurveyViewPager)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                MainFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
