package com.alireza.database.dataSource.image

import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.alireza.database.database.ImageDatabase
import com.alireza.database.model.image.ImageEntity
import javax.inject.Inject

internal class ImageDataSourceImpl @Inject constructor(private val dataBase: ImageDatabase) :
    ImageDataSource {

    override suspend fun insertImages(loadType: LoadType,query:String, imageList: List<ImageEntity>) =
        dataBase.withTransaction {
            if(loadType == LoadType.REFRESH)
                dataBase.imageDao().clearTable(query)
            dataBase.imageDao().insertImages(imageList)
        }

    override suspend fun findImageById(imageId: Int): ImageEntity =
        dataBase.imageDao().findImageById(imageId)

    override suspend fun clearTable(query:String) = dataBase.imageDao().clearTable(query)

    override fun pagingSource(query: String): PagingSource<Int, ImageEntity> =
        dataBase.imageDao().pagingSource(query)

}
