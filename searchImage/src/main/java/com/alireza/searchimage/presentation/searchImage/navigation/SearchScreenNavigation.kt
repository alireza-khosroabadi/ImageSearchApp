package com.alireza.searchimage.presentation.searchImage.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alireza.searchimage.presentation.searchImage.screen.SearchImageScreen

const val searchImageRout = "search_image_rout"



fun NavGraphBuilder.searchImageScreen(navigateToImageDetail: (String)->Unit) {
    composable(searchImageRout) {
       SearchImageScreen(navigateToImageDetail = navigateToImageDetail)
    }
}
