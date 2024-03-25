package com.alireza.searchimage.domain.model.searchImage


data class ImageList(
    val images: List<Image>,
    val total: Int,
    val totalHits: Int
)
