package com.chalat.surveys.feature.survey.data

import io.reactivex.Observable

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
class SurveyRepository(private val remoteDataSource: SurveyDataSource)
    : SurveyDataSource {

    override fun getSurveys(page: Int?): Observable<List<Survey>> {
        return remoteDataSource.getSurveys(page)
    }

}
