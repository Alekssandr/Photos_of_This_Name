package com.paging.photosofthisname.data.model.photoinfo

import com.google.gson.annotations.SerializedName


data class OwnerEntity (
	@SerializedName("username") val username : String,
	@SerializedName("realname") val realname : String
)