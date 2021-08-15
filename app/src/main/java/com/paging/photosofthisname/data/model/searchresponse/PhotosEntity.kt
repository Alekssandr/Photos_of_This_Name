package com.paging.photosofthisname.data.model.searchresponse

import com.google.gson.annotations.SerializedName

data class PhotosEntity (
	@SerializedName("page") val page : Int,
	@SerializedName("pages") val pages : Int,
	@SerializedName("perpage") val perpage : Int,
	@SerializedName("total") val total : Int,
	@SerializedName("photo") val photo : List<SearchPhotoEntity>
)