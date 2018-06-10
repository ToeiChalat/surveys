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
import com.chalat.surveys.base.BasePresenter
import com.chalat.surveys.feature.survey.data.Survey
import com.chalat.surveys.feature.survey.data.SurveyRemoteDataSource
import com.chalat.surveys.feature.survey.data.SurveyRepository
import com.chalat.surveys.network.NetworkProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    private lateinit var adapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MainViewPagerAdapter(childFragmentManager)
        adapter.setOnLastPageListener(object : MainViewPagerAdapter.LastPageListener {
            override fun onLastPage() {
                presenter.loadMore()
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
        mainRefreshAction.setOnClickListener { presenter.refresh() }
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
        presenter.start()
    }

    override fun refresh() {
        mainProgressBar.visibility = View.VISIBLE
        mainContentLayout.visibility = View.GONE
        mainSurveyViewPager.currentItem = 0
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun updateView(surveyList: List<Survey>) {
        mainProgressBar.visibility = GONE
        mainContentLayout.visibility = VISIBLE
        adapter.replaceData(surveyList)
        mainSurveyIndicator.invalidate()
    }

    override fun addMoreView(surveyList: List<Survey>) {
        adapter.appendData(surveyList)
        mainSurveyIndicator.invalidate()
    }

    override fun showToast(message: String) {
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
