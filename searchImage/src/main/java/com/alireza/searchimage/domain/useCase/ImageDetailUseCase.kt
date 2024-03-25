package com.alireza.searchimage.domain.useCase

import com.alireza.searchimage.domain.model.imageDetail.ImageDetail
import com.alireza.searchimage.domain.repository.SearchImageRepository
import javax.inject.Inject

class ImageDetailUseCase @Inject constructor(private val searchImageRepository: SearchImageRepository) {

    suspend operator fun invoke(imageId:Int):ImageDetail =
        searchImageRepository.imageDetails(imageId)
}
