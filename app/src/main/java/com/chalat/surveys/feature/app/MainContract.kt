package com.chalat.surveys.feature.app

import com.chalat.surveys.base.BasePresenter
import com.chalat.surveys.base.BaseView
import com.chalat.surveys.feature.survey.data.Survey

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
interface MainContract {

    interface View : BaseView<Presenter> {

        fun updateView(surveyList: List<Survey>)

        fun showToast(message: String)

        fun refresh()

        fun addMoreView(surveyList: List<Survey>)

    }

    interface Presenter : BasePresenter {

        fun loadMore()

        fun loadData(page: Int = 1)

        fun refresh()

    }

}
