package com.alireza.searchimage.data.repository.searchImage

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import androidx.paging.map
import com.alireza.database.dataSource.image.ImageDataSource
import com.alireza.database.model.image.ImageEntity
import com.alireza.searchimage.data.local.mapper.toImage
import com.alireza.searchimage.data.local.mapper.toImageDetail
import com.alireza.searchimage.data.mediator.SearchImageMediator
import com.alireza.searchimage.data.remote.apiService.ApiService
import com.alireza.searchimage.domain.model.imageDetail.ImageDetail
import com.alireza.searchimage.domain.model.searchImage.Image
import com.alireza.searchimage.domain.repository.SearchImageRepository
import com.alireza.utility.network.NetworkConnection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class SearchImageRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val imageDataSource: ImageDataSource,
    private val networkConnection: NetworkConnection
) : SearchImageRepository {

    override fun searchImage(query: String): Flow<PagingData<Image>> =
        if (networkConnection.isInternetOn())
            onlineModePager(query)
        else
            offlineModePager(query)


    override suspend fun imageDetails(imageId: Int): ImageDetail {
        return imageDataSource.findImageById(imageId).toImageDetail()
    }

    private fun offlineModePager(query: String): Flow<PagingData<Image>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { imageDataSource.pagingSource(query) }
    ).flow
        .map { pagingData -> pagingData.map { it.toImage() } }

    private fun onlineModePager(query: String): Flow<PagingData<Image>> = Pager(
        config = PagingConfig(pageSize = 30),
        remoteMediator = createRemoteMediator(query),
        pagingSourceFactory = { imageDataSource.pagingSource(query) }
    ).flow
        .map { pagingData -> pagingData.map { it.toImage() } }

    private fun createRemoteMediator(query: String): RemoteMediator<Int, ImageEntity> {
        return SearchImageMediator(
            searchQuery = query,
            dataSource = imageDataSource,
            apiService = apiService
        )
    }
}

