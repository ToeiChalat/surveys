package com.chalat.surveys.network

import com.chalat.surveys.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
object NetworkProvider {

    private const val ACCESS_TOKEN = "access_token"

    private var retrofit: Retrofit? = null
    private var haveRefreshToken = false

    fun provideSurveyService(): SurveyService {
        retrofit = retrofit ?: provideRetrofit()
        return retrofit!!.create(SurveyService::class.java)
    }

    private fun provideClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(tokenAuthenticator)
                .authenticator(authenticator)
                .build()
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .client(provideClient())
                .baseUrl("https://nimbl3-survey-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private val tokenAuthenticator: (Interceptor.Chain) -> Response = {
        val newUrl = it.request().url().newBuilder()
                .setQueryParameter(ACCESS_TOKEN, TokenManager.getToken())
                .build()
        val request = it.request().newBuilder()
                .url(newUrl)
                .build()
        it.proceed(request)
    }

    private val authenticator = Authenticator { _, response ->
        if (haveRefreshToken) {
            haveRefreshToken = false
            null
        } else {
            haveRefreshToken = true
            val newToken = provideSurveyService().refreshToken(
                    BuildConfig.USER_NAME,
                    BuildConfig.PASSWORD
            ).execute().body()
            newToken?.let {
                TokenManager.setToken(it.accessToken)
                response.request().newBuilder()
                        .url(response.request().url().newBuilder()
                                .setQueryParameter(ACCESS_TOKEN, it.accessToken)
                                .build())
                        .build()
            }
        }
    }

}
