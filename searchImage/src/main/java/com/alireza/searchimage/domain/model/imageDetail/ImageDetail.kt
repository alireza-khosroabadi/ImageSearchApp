package com.alireza.searchimage.domain.model.imageDetail

data class ImageDetail (
    val id: Int,
    val largeImageURL: String,
    val likes: String,
    val tags: String,
    val user: String,
    val comments: String,
    val downloads: String,
    ){
    val tagsAsList = tags.split(",")
}
