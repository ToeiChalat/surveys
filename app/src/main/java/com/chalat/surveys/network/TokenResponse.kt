package com.chalat.surveys.network

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
data class TokenResponse(
        @SerializedName("access_token")     val accessToken: String,
        @SerializedName("token_type")       val tokenType: String
)