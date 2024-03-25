package com.alireza.searchimage.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.alireza.searchimage.domain.model.imageDetail.ImageDetail
import com.alireza.searchimage.domain.model.searchImage.Image
import kotlinx.coroutines.flow.Flow

interface SearchImageRepository {
   fun searchImage(query: String): Flow<PagingData<Image>>
   suspend fun imageDetails(imageId:Int): ImageDetail
}
