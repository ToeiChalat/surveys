package com.chalat.surveys.feature.survey.data

import com.chalat.surveys.network.SurveyService
import io.reactivex.Observable

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
class SurveyRemoteDataSource(private val service: SurveyService) : SurveyDataSource {

    override fun getSurveys(page: Int?): Observable<List<Survey>> {
        return service.getSurveys(page, PAGE_LIMIT)
    }

    companion object {
        private const val PAGE_LIMIT = 5
    }

}