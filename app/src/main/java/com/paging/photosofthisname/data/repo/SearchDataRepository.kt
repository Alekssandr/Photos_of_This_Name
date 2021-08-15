package com.paging.photosofthisname.data.repo


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.paging.photosofthisname.data.SearchPagingSource
import com.paging.photosofthisname.data.api.SearchAPI
import com.paging.photosofthisname.domain.model.FlickrPhoto
import com.paging.photosofthisname.domain.repo.SearchRepository
import kotlinx.coroutines.flow.Flow

private const val NETWORK_PAGE_SIZE = 5

class SearchDataRepository(
    private val api: SearchAPI
) : SearchRepository {

    override fun getSearchResult(contact: String): Flow<PagingData<FlickrPhoto>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { SearchPagingSource(api,contact) }
        ).flow
    }

}





