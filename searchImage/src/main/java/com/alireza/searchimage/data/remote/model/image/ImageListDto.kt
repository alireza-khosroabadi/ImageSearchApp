package com.alireza.searchimage.data.remote.model.image


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageListDto(
    @SerialName("hits")
    val imageRespons: List<ImageDto>,
    @SerialName("total")
    val total: Int,
    @SerialName("totalHits")
    val totalHits: Int
)
