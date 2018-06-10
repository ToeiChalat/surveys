package com.chalat.surveys.feature.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.chalat.surveys.BuildConfig
import com.chalat.surveys.R
import com.chalat.surveys.feature.survey.data.Survey
import com.chalat.surveys.feature.survey.data.SurveyRemoteDataSource
import com.chalat.surveys.feature.survey.data.SurveyRepository
import com.chalat.surveys.network.NetworkProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private lateinit var adapter: MainViewPagerAdapter

    private val surveyRepository = SurveyRepository(
            SurveyRemoteDataSource(
                    BuildConfig.ACCESS_TOKEN,
                    NetworkProvider.provideSurveyService()
            )
    )

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
        mainRefreshAction.setOnClickListener { loadData() }
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
//        mockData()
        loadData(true)
    }

    private fun loadData(isFirstLoad: Boolean = false) {
        if (isFirstLoad) {
            mainProgressBar.visibility = VISIBLE
            mainContentLayout.visibility = GONE
        }
        surveyRepository.getSurveys()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ updateView(it, isFirstLoad) }, { it.printStackTrace() })
    }

    private fun updateView(it: List<Survey>, isFirstLoad: Boolean) {
        mainProgressBar.visibility = GONE
        mainContentLayout.visibility = VISIBLE
        adapter.replaceData(it)
        if (!isFirstLoad) {
            Toast.makeText(context, "Refreshed Data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mockData() {
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
