package com.chalat.surveys.feature.survey.data

import io.reactivex.Observable

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
interface SurveyDataSource {

    fun getSurveys(page: Int? = null): Observable<List<Survey>>

}
