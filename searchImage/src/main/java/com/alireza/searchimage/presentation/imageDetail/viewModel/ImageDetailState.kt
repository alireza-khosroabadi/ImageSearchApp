package com.alireza.searchimage.presentation.imageDetail.viewModel

import com.alireza.searchimage.domain.model.imageDetail.ImageDetail
import javax.annotation.concurrent.Immutable

@Immutable
data class ImageDetailState(
    val image: ImageDetail?=null,
    val isLoading: Boolean = false
)
