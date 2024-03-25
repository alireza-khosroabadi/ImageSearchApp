package com.alireza.imageSearchApp.searchimage.domain.repository

import androidx.paging.PagingData
import com.alireza.searchimage.domain.model.imageDetail.ImageDetail
import com.alireza.searchimage.domain.model.searchImage.Image
import com.alireza.searchimage.domain.repository.SearchImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSearchImageRepositoryImpl : SearchImageRepository {
    override fun searchImage(query: String): Flow<PagingData<Image>> =
        flowOf(PagingData.from(listOf(
            Image(0,1 ,"", "", "0", 0),
            Image(1, 2,"", "", "0", 0),
            Image(2, 3,"", "", "0", 0),
            Image(3, 4,"", "", "0", 0)
        )))


    override suspend fun imageDetails(imageId: Int): ImageDetail =
        ImageDetail(0, "", 0, listOf(), "", 0, 0)
}
