package com.paging.photosofthisname.data.model.photoinfo

import com.google.gson.annotations.SerializedName


data class PhotoInfoEntity (
	@SerializedName("photo") val photo : PhotoEntity,
	@SerializedName("stat") val stat : String
)