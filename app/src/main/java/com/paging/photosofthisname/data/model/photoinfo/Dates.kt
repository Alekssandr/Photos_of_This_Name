package com.paging.photosofthisname.data.model.photoinfo

import com.google.gson.annotations.SerializedName

data class Dates (

	@SerializedName("posted") val posted : Int,
	@SerializedName("taken") val taken : String,
	@SerializedName("takengranularity") val takengranularity : Int,
	@SerializedName("takenunknown") val takenunknown : Int,
	@SerializedName("lastupdate") val lastupdate : Int
)