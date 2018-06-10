package com.chalat.surveys.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chalat.surveys.R

/**
 *
 * Created by Chalat Chansima on 6/10/18.
 *
 */
fun ImageView.setImage(imageUrl: String) {
    Glide.with(this)
            .load(imageUrl)
            .apply(RequestOptions().apply {
                placeholder(R.color.grey)
            })
            .into(this)
}