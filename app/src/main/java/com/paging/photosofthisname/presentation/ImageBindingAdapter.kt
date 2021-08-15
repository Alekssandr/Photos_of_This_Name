package com.paging.photosofthisname.presentation

import android.widget.ImageView
import com.paging.photosofthisname.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)).fitCenter().override(600, 1200)
            .into(this)
    }
}