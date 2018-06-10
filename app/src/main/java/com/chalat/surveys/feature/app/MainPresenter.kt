package com.chalat.surveys.feature.app

import com.chalat.surveys.feature.survey.data.Survey
import com.chalat.surveys.feature.survey.data.SurveyDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
class MainPresenter(private val surveyRepository: SurveyDataSource,
                    private val view: MainContract.View) : MainContract.Presenter {

    private var currentPage = 1
    private var isAllDataLoaded = false

    init {
        view.setPresenter(this)
    }

    override fun start() {
        loadData()
    }

    override fun loadMore() {
        if (!isAllDataLoaded) {
            currentPage++
            loadData(currentPage)
        }
    }


    override fun loadData(page: Int) {
        if (view.isActive()) {
            if (page != 1) view.showToast("Loading more survey...")
            surveyRepository.getSurveys(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ updateView(it, page) }, { it.printStackTrace() })
        }
    }

    override fun refresh() {
        if (view.isActive()) {
            view.refresh()
            currentPage = 1
            isAllDataLoaded = false
            surveyRepository.getSurveys(currentPage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ updateView(it, REFRESH_DATA) }, { it.printStackTrace() })
        }
    }

    private fun updateView(surveyList: List<Survey>, page: Int) {
        if (view.isActive()) {
            if (surveyList.isNotEmpty()) {
                when (page) {
                    REFRESH_DATA -> {
                        view.updateView(surveyList)
                    }
                    1 -> {
                        view.updateView(surveyList)
                    }
                    else -> {
                        view.addMoreView(surveyList)
                        view.showToast("${surveyList.size} surveys added")
                    }
                }
            } else {
                view.showToast("all survey loaded")
                isAllDataLoaded = true
            }
        }
    }

    companion object {
        private const val REFRESH_DATA = 0
    }

}