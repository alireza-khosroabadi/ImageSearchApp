package com.alireza.database.dao.image

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.alireza.database.model.image.ImageEntity

@Dao
interface ImageDao {
    @Upsert
    suspend fun insertImages(imageList:List<ImageEntity>)

    @Query("DELETE FROM ImageEntity WHERE tags LIKE '%' || :query || '%' ")
    suspend fun clearTable(query:String)

    @Query("SELECT * FROM ImageEntity")
    fun pagingSource():PagingSource<Int, ImageEntity>

    @Query("SELECT * FROM ImageEntity WHERE tags LIKE '%' || :query || '%' ")
    fun pagingSource(query:String):PagingSource<Int, ImageEntity>

    @Query("SELECT * FROM ImageEntity WHERE id=:imageId")
    suspend fun findImageById(imageId:Int): ImageEntity
}
