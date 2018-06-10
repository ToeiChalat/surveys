package com.chalat.surveys.feature.survey.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
@Parcelize
data class Survey(
        @SerializedName("id")               val surveyId: String,
        @SerializedName("title")            val surveyName: String,
        @SerializedName("description")      val surveyDescription: String,
        @SerializedName("cover_image_url")  val surveyImageCoverUrl: String
) : Parcelable