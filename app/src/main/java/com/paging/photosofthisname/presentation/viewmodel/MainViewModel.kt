package com.paging.photosofthisname.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.paging.photosofthisname.domain.model.FlickrPhoto
import com.paging.photosofthisname.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.Flow


class MainViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel(){

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<FlickrPhoto>>? = null

     fun getRecentPhotos(contact: String): Flow<PagingData<FlickrPhoto>> {
        val lastResult = currentSearchResult
        if (contact == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = contact
        val newResult: Flow<PagingData<FlickrPhoto>> =
            searchUseCase.searchPhotoBy(contact).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}

