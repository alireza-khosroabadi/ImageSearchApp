package com.alireza.database.dataSource.image

import androidx.paging.LoadType
import androidx.paging.PagingSource
import com.alireza.database.model.image.ImageEntity

interface ImageDataSource {
    suspend fun clearTable(query:String)
    fun pagingSource(query:String): PagingSource<Int, ImageEntity>
    suspend fun insertImages(loadType: LoadType, query:String, imageList: List<ImageEntity>)
    suspend fun findImageById(imageId: Int): ImageEntity
//    suspend fun insertImages(
//        loadType: LoadType,
//        query: String,
//        page: Int,
//        endOfPaginationReached: Boolean,
//        imageList: List<ImageEntity>
//    )
}
