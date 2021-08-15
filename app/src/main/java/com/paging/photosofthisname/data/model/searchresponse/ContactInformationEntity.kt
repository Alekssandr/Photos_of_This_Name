package com.paging.photosofthisname.data.model.searchresponse

import com.google.gson.annotations.SerializedName


data class ContactInformationEntity (
	@SerializedName("photos") val photosEntity : PhotosEntity,
	@SerializedName("stat") val stat : String
)