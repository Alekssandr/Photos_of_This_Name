package com.paging.photosofthisname.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.paging.photosofthisname.data.api.SearchAPI
import com.paging.photosofthisname.data.model.photoinfo.PhotoEntity
import com.paging.photosofthisname.data.model.searchresponse.PhotosEntity
import com.paging.photosofthisname.domain.model.FlickrPhoto


/**
 * Current implementation defines the source of data and how to retrieve data from that source
 */
class SearchPagingSource(
    private val api: SearchAPI,
    private val contact: String
) : PagingSource<Int, FlickrPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FlickrPhoto> {
        val position = params.key ?: SEARCH_STARTING_PAGE_INDEX
        return try {
            val response = api.getSearchResult(position, contact)
            val data = response.photosEntity.getPhotoResult()
            val nextKey = if (data.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = data,
                prevKey = if (position == SEARCH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey?.let { it+1 }
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    private suspend fun PhotosEntity.getPhotoResult(): List<FlickrPhoto> {
        return photo.map {
            val result = api.getPhotoInfo(it.id).photo
            FlickrPhoto(result.owner.realname, result.description.content, result.dates.taken, result.getPhotoUrl("m"), result.getPhotoUrl("c"))
        }
    }

    private fun PhotoEntity.getPhotoUrl(size: String): String {
        return "$FLICKR_IMAGE_URL$server/${id}_${secret}_$size.jpg"
    }

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, FlickrPhoto>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object{
        private const val SEARCH_STARTING_PAGE_INDEX = 1
        private const val NETWORK_PAGE_SIZE = 30
        private const val FLICKR_IMAGE_URL = "https://live.staticflickr.com/"
    }
}
