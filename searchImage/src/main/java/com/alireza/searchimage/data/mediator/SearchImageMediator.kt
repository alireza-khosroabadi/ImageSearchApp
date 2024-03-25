package com.alireza.searchimage.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.alireza.database.dataSource.image.ImageDataSource
import com.alireza.database.model.image.ImageEntity
import com.alireza.network.BuildConfig
import com.alireza.searchimage.data.local.mapper.toEntity
import com.alireza.searchimage.data.remote.apiService.ApiService
import retrofit2.HttpException
import java.io.IOException

private const val initialPageNumber = 1


@OptIn(ExperimentalPagingApi::class)
class SearchImageMediator(
    private val searchQuery: String,
    private val dataSource: ImageDataSource,
    private val apiService: ApiService
) : RemoteMediator<Int, ImageEntity>() {

    private var nextPage = initialPageNumber

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> initialPageNumber
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) return MediatorResult.Success(endOfPaginationReached = true)
                    else
                        (nextPage/ state.config.pageSize )+1
                }
            }

            val searchedImage = apiService.searchImage(BuildConfig.API_KEY,searchQuery,loadKey, state.config.pageSize)
            nextPage += searchedImage.imageRespons.size
            dataSource.insertImages(loadType, query = searchQuery, imageList = searchedImage.imageRespons.map { it.toEntity() })

            MediatorResult.Success(
                endOfPaginationReached = searchedImage.imageRespons.isEmpty()
            )

        }catch (e: IOException){
            MediatorResult.Error(e)
        }catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }
}
