package com.chalat.surveys.feature.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chalat.surveys.R
import com.chalat.surveys.feature.survey.data.Survey
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private lateinit var adapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        adapter = MainViewPagerAdapter(childFragmentManager)
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
            adapter = this@MainFragment.adapter
        }
        val circleRadius = resources.getDimensionPixelSize(R.dimen.circle_page_indicator_size).toFloat()
        mainSurveyIndicator.apply {
            radius = circleRadius
            setViewPager(mainSurveyViewPager)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.replaceData(listOf(
                Survey("1", "Survey 1", "survey Desc 1", "https://images.unsplash.com/photo-1494367067577-502b2c602fcc?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=4a264890b0119b7be54c642939f477b3&auto=format&fit=crop&w=3089&q=80"),
                Survey("2", "Survey 2", "survey Desc 2", "https://images.unsplash.com/photo-1494367067577-502b2c602fcc?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=4a264890b0119b7be54c642939f477b3&auto=format&fit=crop&w=3089&q=80"),
                Survey("3", "Survey 3", "survey Desc 3", "https://images.unsplash.com/photo-1494367067577-502b2c602fcc?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=4a264890b0119b7be54c642939f477b3&auto=format&fit=crop&w=3089&q=80"),
                Survey("4", "Survey 4", "survey Desc 4", "https://images.unsplash.com/photo-1494367067577-502b2c602fcc?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=4a264890b0119b7be54c642939f477b3&auto=format&fit=crop&w=3089&q=80")
        ))
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
