package com.alireza.searchimage.data.local.mapper

import com.alireza.database.model.image.ImageEntity
import com.alireza.searchimage.data.remote.model.image.ImageDto
import com.alireza.searchimage.domain.model.imageDetail.ImageDetail
import com.alireza.searchimage.domain.model.searchImage.Image

fun ImageDto.toEntity():ImageEntity = ImageEntity(
    id= id,
    previewURL= previewURL,
    tags= tags,
    user = user,
    userId= userId,
    largeImageURL = largeImageURL,
    likes = likes,
    comments = comments,
    downloads = downloads
)

fun ImageEntity.toImage(): Image = Image(
    id= id.toString(),
    previewURL= previewURL,
    tags= tags,
    user = user,
    userId= userId,
    uniqueId = bdId
)


fun ImageEntity.toImageDetail(): ImageDetail= ImageDetail(
    id= id,
    tags = tags,
    user = user,
    largeImageURL = largeImageURL,
    likes = likes.toString(),
    comments = comments.toString(),
    downloads = downloads.toString()
)
