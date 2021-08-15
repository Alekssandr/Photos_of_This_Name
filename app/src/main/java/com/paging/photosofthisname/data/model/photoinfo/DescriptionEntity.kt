package com.paging.photosofthisname.data.model.photoinfo

import com.google.gson.annotations.SerializedName

data class DescriptionEntity (
	@SerializedName("_content") val content : String
)