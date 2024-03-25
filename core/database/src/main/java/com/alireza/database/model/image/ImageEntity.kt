package com.alireza.database.model.image

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    val bdId: Int=0,
    @PrimaryKey val id: Int,
    val largeImageURL: String,
    val previewURL: String,
    val likes: Int,
    val tags: String,
    val user: String,
    val userId: Int,
    val comments: Int,
    val downloads: Int,
){
}
