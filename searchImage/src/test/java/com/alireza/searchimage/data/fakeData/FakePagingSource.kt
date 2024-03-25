package com.alireza.searchimage.data.fakeData

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alireza.database.model.image.ImageEntity

// Define a fake PagingSource implementation
class FakePagingSource(
    private val items: List<ImageEntity>
) : PagingSource<Int, ImageEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageEntity> {
        val key = params.key ?: 0

        return try {
            // Simulate loading data from a data source
            LoadResult.Page(
                data = items,
                prevKey = if (key > 0) key - 1 else null,
                nextKey = if (items.isNotEmpty()) key + 1 else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageEntity>): Int? {
       return null
    }
}
