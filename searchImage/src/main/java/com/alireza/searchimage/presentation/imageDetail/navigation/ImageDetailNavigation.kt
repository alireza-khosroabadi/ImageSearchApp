package com.alireza.searchimage.presentation.imageDetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alireza.searchimage.presentation.imageDetail.screen.ImageDetailScreen


internal const val imageIdArg = "imageId"
const val imageDetailRout = "image_detail_route/{$imageIdArg}"

fun NavGraphBuilder.imageDetailScreen(onNavigateBackClick: ()->Unit) {
    composable(imageDetailRout) {
        ImageDetailScreen(onBackClick = { onNavigateBackClick() })
    }
}

fun NavController.navigateToImageDetail(imageId:String) {
    this.navigate(
        imageDetailRout
        .replace("{$imageIdArg}", imageId,

    ))
}
