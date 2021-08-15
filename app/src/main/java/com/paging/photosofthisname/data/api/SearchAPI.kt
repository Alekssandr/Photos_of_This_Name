package com.paging.photosofthisname.data.api


import com.paging.photosofthisname.data.model.photoinfo.PhotoInfoEntity
import com.paging.photosofthisname.data.model.searchresponse.ContactInformationEntity
import retrofit2.http.GET
import retrofit2.http.Query

const val PER_PAGE = 3

interface SearchAPI {

    @GET("?method=flickr.photos.search&per_page=$PER_PAGE")
    suspend fun getSearchResult(
        @Query("page") page: Int,
        @Query("text") contact: String
    ): ContactInformationEntity

    @GET("?method=flickr.photos.getInfo")
    suspend fun getPhotoInfo(
        @Query("photo_id") photoId: Long
    ): PhotoInfoEntity
}
