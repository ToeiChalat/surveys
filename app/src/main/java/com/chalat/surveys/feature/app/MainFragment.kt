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
    private var currentPage = 1
    private var isAllDataLoaded = false

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
        adapter.setOnLastPageListener(object : MainViewPagerAdapter.LastPageListener {
            override fun onLastPage() {
                if (!isAllDataLoaded) {
                    currentPage++
                    loadData(currentPage)
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainRefreshAction.setOnClickListener { loadData(REFRESH_DATA) }
        mainMenuAction.setOnClickListener { showToast("Menu") }
        mainSurveyViewPager.apply {
            adapter = this@MainFragment.adapter
        }
        val circleRadius = resources.getDimensionPixelSize(R.dimen.circle_page_indicator_size).toFloat()
        mainSurveyIndicator.apply {
            radius = circleRadius
            setViewPager(mainSurveyViewPager)
        }
        loadData()
    }

    private fun loadData(page: Int = 1) {
        if (page == REFRESH_DATA) {
            mainProgressBar.visibility = VISIBLE
            mainContentLayout.visibility = GONE
            currentPage = 1
            isAllDataLoaded = false
            mainSurveyViewPager.currentItem = 0
            surveyRepository.getSurveys(currentPage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ updateView(it, page) }, { it.printStackTrace() })
        } else {
            if (page != 1) showToast("Loading more survey...")
            surveyRepository.getSurveys(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ updateView(it, page) }, { it.printStackTrace() })
        }
    }

    private fun updateView(surveyList: List<Survey>, page: Int) {
        mainProgressBar.visibility = GONE
        mainContentLayout.visibility = VISIBLE
        if (surveyList.isNotEmpty()) {
            when (page) {
                REFRESH_DATA -> {
                    adapter.replaceData(surveyList)
                }
                1 -> {
                    adapter.replaceData(surveyList)
                }
                else -> {
                    adapter.appendData(surveyList)
                    showToast("${surveyList.size} surveys added")
                }
            }
        } else {
            showToast("all survey loaded")
            isAllDataLoaded = true
        }
        mainSurveyIndicator.invalidate()
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {

        private const val REFRESH_DATA = 0

        @JvmStatic
        fun newInstance() =
                MainFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
