package com.paging.photosofthisname.domain.model

data class FlickrPhoto(
    val owner: String,
    val description: String,
    val datesTaken: String,
    val imageUrlSmall: String,
    val imageUrlMedium: String
)