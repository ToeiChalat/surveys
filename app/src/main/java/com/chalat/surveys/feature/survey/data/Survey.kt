package com.chalat.surveys.feature.survey.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
@Parcelize
data class Survey(
        val surveyId: String,
        val surveyName: String,
        val surveyDescription: String,
        val surveyImageCoverUrl: String
) : Parcelable