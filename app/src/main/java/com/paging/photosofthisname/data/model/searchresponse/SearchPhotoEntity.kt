package com.paging.photosofthisname.data.model.searchresponse

import com.google.gson.annotations.SerializedName

data class SearchPhotoEntity (
	@SerializedName("id") val id : Long,
	@SerializedName("owner") val owner : String,
	@SerializedName("secret") val secret : String,
	@SerializedName("server") val server : Int,
	@SerializedName("farm") val farm : Int,
	@SerializedName("title") val title : String,
	@SerializedName("ispublic") val ispublic : Int,
	@SerializedName("isfriend") val isfriend : Int,
	@SerializedName("isfamily") val isfamily : Int
)