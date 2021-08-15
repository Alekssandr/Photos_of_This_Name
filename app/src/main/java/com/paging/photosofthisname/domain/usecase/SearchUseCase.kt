package com.paging.photosofthisname.domain.usecase

import androidx.paging.PagingData
import com.paging.photosofthisname.domain.model.FlickrPhoto
import com.paging.photosofthisname.domain.repo.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val searchRepository: SearchRepository) {
     fun searchPhotoBy(contact: String): Flow<PagingData<FlickrPhoto>> {
        return searchRepository.getSearchResult(contact)
    }
}