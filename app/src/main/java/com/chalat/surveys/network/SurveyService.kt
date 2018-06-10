package com.chalat.surveys.network

import com.chalat.surveys.feature.survey.data.Survey
import io.reactivex.Observable
import retrofit2.http.*

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
interface SurveyService {

    @GET("surveys.json")
    fun getSurveys(@Query("access_token") accessToken: String,
                   @Query("page") page: Int? = null,
                   @Query("per_page") limit: Int? = null): Observable<List<Survey>>

}