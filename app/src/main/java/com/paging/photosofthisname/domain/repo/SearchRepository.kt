package com.paging.photosofthisname.domain.repo

import androidx.paging.PagingData
import com.paging.photosofthisname.domain.model.FlickrPhoto
import kotlinx.coroutines.flow.Flow


interface SearchRepository {
     fun getSearchResult(contact:String): Flow<PagingData<FlickrPhoto>>
}