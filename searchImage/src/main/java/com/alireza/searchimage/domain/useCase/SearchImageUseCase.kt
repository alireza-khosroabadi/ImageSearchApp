package com.alireza.searchimage.domain.useCase

import androidx.paging.PagingData
import com.alireza.searchimage.domain.model.searchImage.Image
import com.alireza.searchimage.domain.repository.SearchImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchImageUseCase @Inject constructor(private val searchImageRepository: SearchImageRepository) {

    operator fun invoke(searchQuery:String): Flow<PagingData<Image>> =
        searchImageRepository.searchImage(searchQuery)
}
