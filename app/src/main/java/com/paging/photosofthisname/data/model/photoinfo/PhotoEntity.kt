package com.paging.photosofthisname.data.model.photoinfo

import com.google.gson.annotations.SerializedName


data class PhotoEntity (
    @SerializedName("id") val id : String,
    @SerializedName("secret") val secret : String,
    @SerializedName("server") val server : Int,
    @SerializedName("owner") val owner : OwnerEntity,
    @SerializedName("description") val description : DescriptionEntity,
    @SerializedName("dates") val dates : Dates
)