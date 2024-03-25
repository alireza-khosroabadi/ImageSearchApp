package com.alireza.searchimage.domain.model.searchImage


data class Image(
    val id: String,
    val uniqueId: Int,
    val previewURL: String,
    val tags: String,
    val user: String,
    val userId: Int,
)
